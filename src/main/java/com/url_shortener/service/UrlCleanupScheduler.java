package com.url_shortener.service;

import com.url_shortener.model.Url;
import com.url_shortener.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UrlCleanupScheduler {
    private final UrlRepository urlRepository;
    private final CacheService cacheService;

    @Scheduled(cron = "${app.cleanup.cron}")
    public void deleteExpireUrls() {
        List<Url> expireUrls = urlRepository.findExpiredUrls(LocalDateTime.now());

        for (Url url : expireUrls) {
            cacheService.delete(url.getShortCode());
        }

        urlRepository.deleteAll(expireUrls);
    }
}
