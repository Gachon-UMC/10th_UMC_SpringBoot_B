package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

public class ReviewReqDTO {
    @Builder
    public record CreateReview(
            @NotBlank(message = "리뷰 내용은 필수입니다.")
            String content,

            @NotNull(message = "별점은 필수입니다.")
            @Min(value = 0, message = "별점은 0점 이상이어야 합니다.")
            @Max(value = 5, message = "별점은 5점 이하여야 합니다.")
            Float score,

            @NotNull(message = "사용자 ID는 필수입니다.")
            Long memberId,

            List<String> photoUrls
    ) {}
}
