package com.url_shortener.controller;

import com.url_shortener.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class RedirectController {
    private final UrlService urlService;

    @GetMapping("/{code}")
    public String redirect(@PathVariable String code, Model model) {
        String originalUrl = urlService.getOriginalUrl(code);
        model.addAttribute("code", code);
        model.addAttribute("originalUrl", originalUrl);

        String displayUrl = originalUrl.length() > 55
                ? originalUrl.substring(0,55) + "..."
                : originalUrl;

        model.addAttribute("displayUrl", displayUrl);

        return "redirect";
    }
}
