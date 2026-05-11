package com.example.umc10th.domain.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;

public class ReviewResDTO {

    @Builder
    @Schema(description = "리뷰 작성 응답")
    public record CreateReviewResponse(
            @Schema(description = "리뷰 ID", example = "1")
            Long reviewId,
            @Schema(description = "가게 ID", example = "1")
            Long storeId,
            @Schema(description = "회원 ID", example = "1")
            Long memberId,
            @Schema(description = "평점", example = "4")
            int rating,
            @Schema(description = "리뷰 내용", example = "맛있어요! 또 올게요")
            String content,
            @Schema(description = "작성일")
            LocalDate createdAt
    ) {}
}
