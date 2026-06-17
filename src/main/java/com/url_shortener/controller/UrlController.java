package com.url_shortener.controller;

import com.url_shortener.dto.BatchClickRequest;
import com.url_shortener.dto.ClicksResponse;
import com.url_shortener.dto.ShortenRequest;
import com.url_shortener.dto.ShortenResponse;
import com.url_shortener.service.UrlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/urls")
@RequiredArgsConstructor
public class UrlController {
    private final UrlService urlService;

    @PostMapping("/shorten")
    public ResponseEntity<ShortenResponse> shorten(
            @Valid @RequestBody ShortenRequest request) {
        return ResponseEntity.ok(urlService.shortenUrl(request));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteUrl(@PathVariable String code) {
        urlService.deleteUrl(code);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/batch/clicks")
    public ResponseEntity<List<ClicksResponse>> getClicks(
            @RequestBody @Valid BatchClickRequest request
            ) {
        List<ClicksResponse> responses = urlService.getClicks(request);
        return ResponseEntity.ok(responses);
    }
}
