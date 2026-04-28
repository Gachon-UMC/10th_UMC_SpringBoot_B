package com.example.umc10th.domain.review.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class ReviewResDTO {

    @Builder
    public record CreateReviewResponse(
            Long reviewId,
            Long restaurantId,
            Long userId,
            int rating,
            String content,
            LocalDateTime createdAt
    ) {}
}
