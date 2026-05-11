package com.example.umc10th.domain.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class ReviewReqDTO {

    @Schema(description = "리뷰 작성 요청")
    public record CreateReviewRequest(
            @Schema(description = "회원 ID", example = "1")
            Long memberId,
            @Schema(description = "가게 ID", example = "1")
            Long storeId,
            @Schema(description = "평점 (1~5)", example = "4")
            int rating,
            @Schema(description = "리뷰 내용", example = "맛있어요! 또 올게요")
            String content,
            @Schema(description = "리뷰 이미지 URL 목록")
            List<String> images
    ) {}
}
