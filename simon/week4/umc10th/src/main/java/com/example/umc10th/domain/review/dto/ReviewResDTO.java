package com.example.umc10th.domain.review.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {
    @Builder
    public record CursorPagination<T>(
            List<T> data,
            String nextCursor, // 다음 요청 때 보낼 커서 (별점)
            Boolean hasNext    // 다음 페이지 존재 여부
    ) {}

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
            Float rating,
            String reviewerNickname,
            LocalDateTime createdAt,
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
