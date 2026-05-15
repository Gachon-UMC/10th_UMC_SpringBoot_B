package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // 가게 리뷰 조회 (생성일자 내림차순으로 페이징 처리)
    @Query("""
    select distinct r from Review r
    join fetch r.member
    left join fetch r.reviewPhotoList
    where r.store.id = :storeId
    order by r.createdAt desc
    """)
    Page<Review> findByStoreIdOrderByCreatedAtDesc(Long storeId, Pageable pageable);

    // 특정 가게 내 나의 리뷰 - ID 순 (최신순) 조회
    @Query("""
    SELECT r
    FROM Review r
    WHERE r.member.id = :memberId
      AND r.store.id = :storeId
      AND (
            :cursorId IS NULL
            OR r.id < :cursorId
          )
    ORDER BY r.id DESC
    """)
    Slice<Review> findAllByMemberIdAndStoreIdAndIdLessThanOrderByIdDesc(
            @Param("memberId") Long memberId,
            @Param("storeId") Long storeId,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );

    // 특정 가게 내 나의 리뷰 - 별점 순 조회 (복합 커서: 별점 -> ID 순)
    @Query("""
    SELECT r
    FROM Review r
    WHERE r.member.id = :memberId
      AND r.store.id = :storeId
      AND (
            :cursorRating IS NULL
            OR r.score < :cursorRating
            OR (r.score = :cursorRating AND r.id < :cursorId)
          )
    ORDER BY r.score DESC, r.id DESC
    """)
    Slice<Review> findAllByMemberIdAndStoreIdAndRatingCursorOrder(
            @Param("memberId") Long memberId,
            @Param("storeId") Long storeId,
            @Param("cursorRating") Float cursorRating,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );
}
