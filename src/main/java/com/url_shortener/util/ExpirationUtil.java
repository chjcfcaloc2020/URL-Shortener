package com.url_shortener.util;

import java.time.Duration;

public class ExpirationUtil {
    public static Duration parse(String input) {
        if (input == null || input.isBlank()) {
            return null;
        }

        input = input.trim().toLowerCase();

        long value = Long.parseLong(input.substring(0, input.length() - 1));
        char unit = input.charAt(input.length() - 1);

        return switch (unit) {
            case 'm' -> Duration.ofMinutes(value);
            case 'h' -> Duration.ofHours(value);
            case 'd' -> Duration.ofDays(value);
            default -> throw new IllegalArgumentException("Invalid expiration format");
        };
    }
}
