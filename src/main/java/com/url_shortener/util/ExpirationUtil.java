package com.url_shortener.util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class ExpirationUtil {
    private static final Pattern PATTERN = Pattern.compile("^[1-9]\\d*[mhd]$");

    public static Duration parse(String input) {
        if (input == null || input.isBlank()) {
            return null;
        }

        input = input.trim().toLowerCase();

        if ("never".equals(input)) {
            return null;
        }

        if (!PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException(
                    "Expiration format must be like 15m, 1h, 1d or never");
        }

        long value = Long.parseLong(input.substring(0, input.length() - 1));
        char unit = input.charAt(input.length() - 1);

        return switch (unit) {
            case 'm' -> Duration.ofMinutes(value);
            case 'h' -> Duration.ofHours(value);
            case 'd' -> Duration.ofDays(value);
            default -> throw new IllegalArgumentException("Invalid expiration format");
        };
    }

    public static Duration remaining(LocalDateTime expiresAt) {
        if (expiresAt == null) {
            return null;
        }

        Duration duration = Duration.between(LocalDateTime.now(), expiresAt);

        return duration.isNegative() ? Duration.ZERO : duration;
    }

    public static LocalDateTime calculateExpiration(String input) {
        Duration duration = parse(input);
        if (duration == null) {
            return null;
        }

        return LocalDateTime.now().plus(duration);
    }
}
