package com.url_shortener.repository;

import com.url_shortener.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> findByShortCode(String shortCode);

    @Query("SELECT u FROM Url u WHERE u.expiresAt IS NOT NULL AND u.expiresAt < :now")
    List<Url> findExpiredUrls(@Param("now") LocalDateTime now);

    @Modifying
    @Transactional
    @Query("""
        UPDATE Url u
        SET u.clicks = u.clicks + 1
        WHERE u.id = :id
    """)
    void incrementClicks(@Param("id") Long id);
}
