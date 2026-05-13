package com.example.umc10th.domain.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ReviewResDTO {

    @Builder
    @Schema(description = "내가 작성한 리뷰 목록 응답 (커서 기반)")
    public record MyReviewListResponse(
            @Schema(description = "리뷰 목록")
            List<MyReviewDTO> reviews,
            @Schema(description = "다음 페이지 커서 (마지막 리뷰 ID)", example = "10")
            Long nextCursor,
            @Schema(description = "다음 페이지 존재 여부")
            boolean hasNext
    ) {}

    @Builder
    @Schema(description = "내가 작성한 리뷰 정보")
    public record MyReviewDTO(
            @Schema(description = "리뷰 ID", example = "1")
            Long reviewId,
            @Schema(description = "가게 이름", example = "맛있는 식당")
            String storeName,
            @Schema(description = "평점", example = "4.5")
            BigDecimal rating,
            @Schema(description = "리뷰 내용", example = "맛있어요!")
            String content,
            @Schema(description = "작성일")
            LocalDate createdAt
    ) {}

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
