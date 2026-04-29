package com.example.umc10th.domain.review.dto;

import lombok.Builder;

import java.util.List;

public class ReviewResDTO {
    @Builder
    public record CreateReview(
            Long reviewId,
            String message,
            String createdAt
    ) {}

    @Builder
    public record ReviewItem(
            Long reviewId,
            String content,
            Float score,
            String reviewerNickname,
            String createdAt,
            List<String> photoUrls
    ) {}

    @Builder
    public record ReviewList(
            List<ReviewItem> reviews,
            Integer page,
            Integer size,
            Boolean hasNext
    ) {}
}
