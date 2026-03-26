package com.url_shortener.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "urls")
@Getter
@Setter
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String originalUrl;

    @Column(unique = true)
    private String shortCode;

    private Long clicks = 0L;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime expiresAt;
}
