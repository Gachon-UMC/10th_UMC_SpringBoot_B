package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 커서 기반 - ID 순 (첫 페이지)
    List<Review> findByMemberIdOrderByIdAsc(Long memberId, Pageable pageable);

    // 커서 기반 - ID 순 (다음 페이지)
    List<Review> findByMemberIdAndIdGreaterThanOrderByIdAsc(Long memberId, Long cursor, Pageable pageable);

    // 커서 기반 - 별점 순 (첫 페이지)
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId ORDER BY r.rating DESC, r.id ASC")
    List<Review> findByMemberIdOrderByRatingDesc(@Param("memberId") Long memberId, Pageable pageable);

    // 커서 기반 - 별점 순 (다음 페이지, compound cursor)
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId " +
            "AND (r.rating < :cursorRating OR (r.rating = :cursorRating AND r.id > :cursorId)) " +
            "ORDER BY r.rating DESC, r.id ASC")
    List<Review> findByMemberIdOrderByRatingDescWithCursor(
            @Param("memberId") Long memberId,
            @Param("cursorRating") BigDecimal cursorRating,
            @Param("cursorId") Long cursorId,
            Pageable pageable);
}
