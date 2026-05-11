package com.example.umc._th.domain.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReviewReqDTO {
    @Schema(name = "CreateReviewRequest")
    public record CreateReview(
            @NotBlank(message = "리뷰 내용은 필수 항목입니다.")
            String content,
            @NotNull(message = "리뷰 평점은 필수 항목입니다.")
            Float star
    ){}
}
