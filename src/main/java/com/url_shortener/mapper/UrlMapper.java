package com.url_shortener.mapper;

import com.url_shortener.dto.ShortenResponse;
import org.springframework.stereotype.Component;


@Component
public class UrlMapper {
    public ShortenResponse toResponse(String shortCode) {
        return ShortenResponse.builder()
                .shortCode(shortCode)
                .build();
    }
}
