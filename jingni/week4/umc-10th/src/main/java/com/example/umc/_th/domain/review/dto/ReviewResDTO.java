package com.example.umc._th.domain.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public class ReviewResDTO {
    @Schema(name = "CreateReviewResponse")
    public record CreateReview(
            Long reviewId,
            Long storeId,
            LocalDateTime createdAt
    ){}
}
