package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    @Query(
            """
            select r
            from Review r
            where r.user.id = :userId 
                        and r.id< :idCursor
            order by r.star desc, r.id desc
            """
    )
    Slice<Review> findReviewByuserIdAndLessThenOrderByIdDesc(
            @Param("userId") Long userId,
            @Param("idCursor") Long idCursor,
            Pageable pageable
    );

    @Query(
            """
            select r
            from Review r
            where r.user.id = :userId 
            order by r.star desc, r.id desc
            """
    )Slice<Review> findReviewByUserIdOrderByIdDesc(
            @Param("userId") Long userId,
            Pageable pageable
    );
}
