package com.url_shortener.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ShortenResponse {
    private String shortCode;
}
