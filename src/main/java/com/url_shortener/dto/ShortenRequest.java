package com.url_shortener.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ShortenRequest {
    @NotBlank(message = "URL must not be empty")
    private String url;

    @NotBlank(message = "Expiration time must not be empty")
    private String expireIn;
}
