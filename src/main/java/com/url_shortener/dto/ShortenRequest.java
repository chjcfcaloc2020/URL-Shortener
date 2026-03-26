package com.url_shortener.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ShortenRequest {
    @NotBlank(message = "URL must not be empty")
    private String url;

    @Min(value = 0, message = "Expire days must be >= 0")
    private Integer expireDays = 0;
}
