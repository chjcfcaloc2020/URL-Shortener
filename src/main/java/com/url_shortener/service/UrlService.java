package com.url_shortener.service;

import com.url_shortener.dto.BatchClickRequest;
import com.url_shortener.dto.ClicksResponse;
import com.url_shortener.dto.ShortenRequest;
import com.url_shortener.dto.ShortenResponse;
import com.url_shortener.exception.payload.NotFoundException;
import com.url_shortener.exception.payload.UrlExpiredException;
import com.url_shortener.mapper.UrlMapper;
import com.url_shortener.model.Url;
import com.url_shortener.repository.UrlRepository;
import com.url_shortener.util.ExpirationUtil;
import com.url_shortener.util.UrlValidation;
import lombok.RequiredArgsConstructor;
import org.hashids.Hashids;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UrlService {
    private final UrlRepository urlRepository;
    private final UrlMapper urlMapper;
    private final UrlValidation urlValidation;
    private final Hashids hashids;
    private final CacheService cacheService;
    private final ClickService clickService;

    public ShortenResponse shortenUrl(ShortenRequest shortenRequest) {
        urlValidation.validate(shortenRequest.getUrl());
        Url url = new Url();
        url.setOriginalUrl(shortenRequest.getUrl());

        // parse expiration
        if (shortenRequest.getExpireIn() != null) {
            url.setExpiresAt(
                    ExpirationUtil.calculateExpiration(shortenRequest.getExpireIn())
            );
        }
        urlRepository.save(url);

        // Encode = hashids
        String code = hashids.encode(url.getId());
        url.setShortCode(code);
        urlRepository.save(url);

        return urlMapper.toResponse(
                code,
                url.getClicks(),
                url.getOriginalUrl(),
                url.getCreatedAt(),
                url.getExpiresAt()
        );
    }

    public String getOriginalUrl(String code) {
        // decode Hashids
        long[] decoded = hashids.decode(code);
        if (decoded.length == 0) {
            throw new NotFoundException("Invalid short URL");
        }

        Long id = decoded[0];
        String cached = cacheService.get(code);
        clickService.updateClicks(id);

        if (cached != null) {
            return cached;
        }

        Url url = urlRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("URL not found"));

        // check expiration
        if (url.getExpiresAt() != null &&
                url.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new UrlExpiredException("URL has expired");
        }

        // save cache
        cacheService.save(code, url.getOriginalUrl(), ExpirationUtil.remaining(url.getExpiresAt()));

        return url.getOriginalUrl();
    }

    @Transactional
    public void deleteUrl(String code) {
        cacheService.delete(code);
        int rows = urlRepository.deleteUrlByShortCode(code);

        if (rows == 0) {
            throw new NotFoundException("URL not found");
        }
    }

    @Transactional(readOnly = true)
    public List<ClicksResponse> getClicks(BatchClickRequest request) {
        return urlRepository.findClicksByShortCode(request.getShortCodes());
    }
}
