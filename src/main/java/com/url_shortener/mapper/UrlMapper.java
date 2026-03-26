package com.url_shortener.mapper;

import com.url_shortener.dto.ShortenResponse;
import com.url_shortener.model.Url;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UrlMapper {
    public Url toEntity(String url, Integer expireDays) {
        Url entity = new Url();
        entity.setOriginalUrl(url);

        if (expireDays != null && expireDays > 0) {
            entity.setExpiresAt(LocalDateTime.now().plusDays(expireDays));
        }

        return entity;
    }

    public ShortenResponse toResponse(String shortCode) {
        return ShortenResponse.builder()
                .shortUrl("http://localhost:8080/" + shortCode)
                .build();
    }
}
