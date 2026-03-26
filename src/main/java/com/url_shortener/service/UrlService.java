package com.url_shortener.service;

import com.url_shortener.dto.ShortenRequest;
import com.url_shortener.dto.ShortenResponse;
import com.url_shortener.exception.payload.NotFoundException;
import com.url_shortener.exception.payload.UrlExpiredException;
import com.url_shortener.mapper.UrlMapper;
import com.url_shortener.model.Url;
import com.url_shortener.repository.UrlRepository;
import com.url_shortener.util.Base62Util;
import com.url_shortener.util.UrlValidation;
import lombok.RequiredArgsConstructor;
import org.hashids.Hashids;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UrlService {
    private final UrlRepository urlRepository;
    private final UrlMapper urlMapper;
    private final UrlValidation urlValidation;
    private final Hashids hashids;

    public ShortenResponse shortenUrl(ShortenRequest shortenRequest) {
        urlValidation.validate(shortenRequest.getUrl());

        Url url = urlMapper.toEntity(shortenRequest.getUrl(), shortenRequest.getExpireDays());
        urlRepository.save(url);

        // Encode = hashids
        String code = hashids.encode(url.getId());
        url.setShortCode(code);
        urlRepository.save(url);

        return urlMapper.toResponse(code);
    }

    public String getOriginalUrl(String code) {
        long[] decoded = hashids.decode(code);

        if (decoded.length == 0) {
            throw new NotFoundException("Invalid short URL");
        }

        Long id = decoded[0];
        Url url = urlRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("URL not found"));

        // check expiration
        if (url.getExpiresAt() != null &&
                url.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new UrlExpiredException("URL has expired");
        }

        url.setClicks(url.getClicks() + 1);
        urlRepository.save(url);

        return url.getOriginalUrl();
    }
}
