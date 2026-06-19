package com.url_shortener.exception;

import com.url_shortener.exception.payload.UrlExpiredException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PageExceptionHandler {
    @Value("${app.base-fe-url}")
    private String feUrl;

    @ExceptionHandler(UrlExpiredException.class)
    public String handleExpired(Model model) {
        model.addAttribute("feUrl", feUrl);
        return "expired";
    }
}
