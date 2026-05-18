package com.example.umc._th.domain.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {
    @Schema(name = "CreateReviewResponse")
    public record CreateReview(
            Long reviewId,
            Long storeId,
            LocalDateTime createdAt
    ){}

    @Schema(name="GetReviewsResponse")
    public record GetReviews(
        List<ReviewInfo> reviews
    ){}

    public record ReviewInfo(
            Long reviewId,
            String storeName,
            String memberName,
            String content,
            BigDecimal star,
            LocalDateTime createdAt
    ){}
}
