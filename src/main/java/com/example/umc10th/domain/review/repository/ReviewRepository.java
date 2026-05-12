package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// 리뷰 Entity의 DB 접근을 담당하는 Repository입니다.
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 커서 없이 회원이 작성한 리뷰를 ID 내림차순으로 조회합니다.
    @Query("""
            SELECT DISTINCT r
            FROM Review r
            LEFT JOIN FETCH r.replies rp
            WHERE r.member.id = :memberId
            ORDER BY r.id DESC
            """)
    Slice<Review> findMyReviewsOrderByIdDesc(@Param("memberId") Long memberId, Pageable pageable);

    // ID 커서 기준으로 회원이 작성한 리뷰를 ID 내림차순으로 조회합니다.
    @Query("""
            SELECT DISTINCT r
            FROM Review r
            LEFT JOIN FETCH r.replies rp
            WHERE r.member.id = :memberId
            AND r.id < :reviewId
            ORDER BY r.id DESC
            """)
    Slice<Review> findMyReviewsByIdCursor(
            @Param("memberId") Long memberId,
            @Param("reviewId") Long reviewId,
            Pageable pageable
    );

    // 커서 없이 회원이 작성한 리뷰를 별점 내림차순, ID 내림차순으로 조회합니다.
    @Query("""
            SELECT DISTINCT r
            FROM Review r
            LEFT JOIN FETCH r.replies rp
            WHERE r.member.id = :memberId
            ORDER BY r.star DESC, r.id DESC
            """)
    Slice<Review> findMyReviewsOrderByStarDesc(@Param("memberId") Long memberId, Pageable pageable);

    // 별점 커서 기준으로 회원이 작성한 리뷰를 별점 내림차순, ID 내림차순으로 조회합니다.
    @Query("""
            SELECT DISTINCT r
            FROM Review r
            LEFT JOIN FETCH r.replies rp
            WHERE r.member.id = :memberId
            AND (
                r.star < :star
                OR (r.star = :star AND r.id < :reviewId)
            )
            ORDER BY r.star DESC, r.id DESC
            """)
    Slice<Review> findMyReviewsByStarCursor(
            @Param("memberId") Long memberId,
            @Param("star") Float star,
            @Param("reviewId") Long reviewId,
            Pageable pageable
    );
}
