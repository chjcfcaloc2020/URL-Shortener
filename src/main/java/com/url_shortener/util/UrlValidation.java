package com.url_shortener.util;

import com.url_shortener.exception.payload.BadRequestException;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Component;

@Component
public class UrlValidation {
    private static final UrlValidator VALIDATOR = new UrlValidator(new String[]{"http", "https"});

    public void validate(String url) {
        if (!VALIDATOR.isValid(url)) {
            throw new BadRequestException("Invalid URL");
        }
    }
}
