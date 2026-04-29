package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;

public class ReviewConverter {

    public static ReviewResDTO.CreateReview createReview(
            Review review
    ){
        return ReviewResDTO.CreateReview.builder()
                .reviewId(review.getId())
                .userId(review.getUser().getId())
                .storeId(review.getStore().getId())
                .reviewComment(review.getReviewContent())
                .star(review.getStar())
                .build();
    }
}
