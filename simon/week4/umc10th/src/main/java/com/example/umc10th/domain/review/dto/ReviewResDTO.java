package com.example.umc10th.domain.review.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    @Builder
    public record CreateReview(
            Long reviewId,
            String message,
            LocalDateTime createdAt
    ) {}

    @Builder
    public record ReviewItem(
            Long reviewId,
            String content,
            Float score,
            String reviewerNickname,
            LocalDateTime createdAt,
            List<String> photoUrls
    ) {}
}
