package com.example.umc10th.domain.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.util.List;

public class ReviewReqDTO {

    @Schema(description = "내가 작성한 리뷰 조회 요청")
    public record MyReviewRequest(
            @NotNull(message = "회원 ID는 필수입니다.")
            @Schema(description = "회원 ID", example = "1")
            Long memberId
    ) {}

    @Schema(description = "리뷰 작성 요청")
    public record CreateReviewRequest(
            @NotNull(message = "회원 ID는 필수입니다.")
            @Schema(description = "회원 ID", example = "1")
            Long memberId,

            @NotNull(message = "가게 ID는 필수입니다.")
            @Schema(description = "가게 ID", example = "1")
            Long storeId,

            @Min(value = 1, message = "평점은 최소 1이어야 합니다.")
            @Max(value = 5, message = "평점은 최대 5이어야 합니다.")
            @Schema(description = "평점 (1~5)", example = "4")
            int rating,

            @NotBlank(message = "리뷰 내용은 필수입니다.")
            @Size(min = 1, max = 500, message = "리뷰 내용은 1자 이상 500자 이하여야 합니다.")
            @Schema(description = "리뷰 내용", example = "맛있어요! 또 올게요")
            String content,

            @Schema(description = "리뷰 이미지 URL 목록")
            List<String> images
    ) {}
}
