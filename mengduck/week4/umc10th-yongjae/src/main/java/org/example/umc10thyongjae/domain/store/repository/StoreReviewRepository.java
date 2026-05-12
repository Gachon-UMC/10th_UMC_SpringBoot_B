package org.example.umc10thyongjae.domain.store.repository;

import org.example.umc10thyongjae.domain.store.entity.StoreReview;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StoreReviewRepository extends JpaRepository<StoreReview, Long> {
    @Query("""
            SELECT r
            FROM StoreReview r
            JOIN FETCH r.store
            JOIN FETCH r.user
            WHERE r.user.id = :userId
              AND (:cursor IS NULL OR r.id < :cursor)
            ORDER BY r.id DESC
            """)
    Slice<StoreReview> findMyReviewListLatest(
            @Param("userId") Long userId,
            @Param("cursor") Long cursor,
            Pageable pageable
    );

    @Query("""
            SELECT r
            FROM StoreReview r
            JOIN FETCH r.store
            JOIN FETCH r.user
            WHERE r.user.id = :userId
              AND (:cursor IS NULL OR r.id > :cursor)
            ORDER BY r.id ASC
            """)
    Slice<StoreReview> findMyReviewListOldest(
            @Param("userId") Long userId,
            @Param("cursor") Long cursor,
            Pageable pageable
    );

    @Query("""
            SELECT r
            FROM StoreReview r
            JOIN FETCH r.store
            JOIN FETCH r.user
            WHERE r.user.id = :userId
              AND (:cursor IS NULL OR r.id < :cursor)
            ORDER BY r.id DESC
            """)
    Slice<StoreReview> findMyReviewListById(
            @Param("userId") Long userId,
            @Param("cursor") Long cursor,
            Pageable pageable
    );

    @Query("""
            SELECT r
            FROM StoreReview r
            JOIN FETCH r.store
            JOIN FETCH r.user
            WHERE r.user.id = :userId
              AND (
                    :cursor IS NULL
                    OR r.id < :cursor
              )
            ORDER BY CAST(r.score AS integer) DESC, r.id DESC
            """)
    Slice<StoreReview> findMyReviewListByScore(
            @Param("userId") Long userId,
            @Param("cursor") Long cursor,
            Pageable pageable
    );
}