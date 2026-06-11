package com.url_shortener.mapper;

import com.url_shortener.dto.ShortenResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UrlMapper {
    public ShortenResponse toResponse(
            String shortCode, Long clicks, String originalUrl, LocalDateTime createdAt, LocalDateTime expiresAt
    ) {
        return ShortenResponse.builder()
                .shortCode(shortCode)
                .clicks(clicks)
                .originalUrl(originalUrl)
                .createdAt(createdAt)
                .expiresAt(expiresAt)
                .build();
    }
}
