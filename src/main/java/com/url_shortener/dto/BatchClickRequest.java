package com.url_shortener.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchClickRequest {
    @NotEmpty(message = "shortCodes cannot be empty")
    private List<String> shortCodes;
}
