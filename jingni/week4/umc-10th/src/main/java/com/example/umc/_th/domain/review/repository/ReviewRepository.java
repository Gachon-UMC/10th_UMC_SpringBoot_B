package com.example.umc._th.domain.review.repository;

import com.example.umc._th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("""
        SELECT r
        FROM Review r
        JOIN FETCH r.store s
        JOIN FETCH r.member m
        WHERE r.member.id = :memberId
          AND (:cursor IS NULL OR r.id < :cursor)
        ORDER BY r.id DESC
    """)
    Slice<Review> findMyReviews(
            @Param("memberId") Long memberId,
            @Param("cursor") Long cursor,
            Pageable pageable
    );
}