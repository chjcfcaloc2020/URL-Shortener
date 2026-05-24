package com.url_shortener.service;

import com.url_shortener.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClickService {
    private final UrlRepository urlRepository;

    @Async
    public void updateClicks(Long id) {
        urlRepository.incrementClicks(id);
    }
}
