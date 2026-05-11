package com.example.umc._th.domain.review.converter;

import com.example.umc._th.domain.member.entity.Member;
import com.example.umc._th.domain.review.dto.ReviewReqDTO;
import com.example.umc._th.domain.review.dto.ReviewResDTO;
import com.example.umc._th.domain.review.entity.Review;
import com.example.umc._th.domain.store.entity.Store;

import java.math.BigDecimal;

public class ReviewConverter {

    public static Review toEntity(
            ReviewReqDTO.CreateReview request,
            Store store,
            Member member
    ){
        return Review.builder()
                .content(request.content())
                .star(BigDecimal.valueOf(request.star()))
                .store(store)
                .member(member)
                .build();
    }

    public static ReviewResDTO.CreateReview toCreateReviewDTO(Review review){
        return new ReviewResDTO.CreateReview(
                review.getId(),
                review.getStore().getId(),
                review.getCreatedAt()
        );
    }
}