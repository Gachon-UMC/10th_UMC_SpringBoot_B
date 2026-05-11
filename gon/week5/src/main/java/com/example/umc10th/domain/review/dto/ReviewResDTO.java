package com.example.umc10th.domain.review.dto;

import lombok.Builder;

public class ReviewResDTO {

    @Builder
    public record CreateReview(
            Long reviewId,
            Long userId,
            Long storeId,
            String reviewComment,
            int star
    ){}
}
