package com.url_shortener.repository;

import com.url_shortener.dto.ClicksResponse;
import com.url_shortener.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UrlRepository extends JpaRepository<Url, Long> {
    @Modifying
    @Transactional
    @Query("""
        UPDATE Url u
        SET u.clicks = u.clicks + 1
        WHERE u.id = :id
    """)
    void incrementClicks(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("""
        DELETE FROM Url u
        WHERE u.shortCode = :shortCode
    """)
    int deleteUrlByShortCode(@Param("shortCode") String shortCode);

    @Query("""
        SELECT new com.url_shortener.dto.ClicksResponse(
            u.shortCode,
            u.clicks
        )
        FROM Url u
        WHERE u.shortCode IN :shortCodes
    """)
    List<ClicksResponse> findClicksByShortCode(
            @Param("shortCodes") List<String> shortCodes
    );
}
