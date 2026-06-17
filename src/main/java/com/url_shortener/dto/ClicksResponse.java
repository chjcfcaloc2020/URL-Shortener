package com.url_shortener.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClicksResponse {
    private String shortCode;
    private Long clicks;
}
