package com.example.umc._th.domain.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class ReviewReqDTO {
    @Schema(name = "CreateReviewRequest")
    public record CreateReview(
            String content,
            Float star
    ){}
}
