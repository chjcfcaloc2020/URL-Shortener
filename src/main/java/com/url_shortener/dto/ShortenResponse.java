package com.url_shortener.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ShortenResponse {
    private String shortCode;
    private Long clicks;
    private String originalUrl;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
}
