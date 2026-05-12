package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

// 요청 DTO 필드 조회를 위한 Getter를 생성합니다.
@Getter
public class ReviewReqDTO {

    // 리뷰와 연결된 사용자 미션 ID입니다.
    @NotNull(message = "사용자 미션 ID는 필수입니다.")
    private Long userMissionId;

    // 리뷰 별점입니다.
    @NotNull(message = "별점은 필수입니다.")
    @Min(value = 1, message = "별점은 1점 이상이어야 합니다.")
    @Max(value = 5, message = "별점은 5점 이하여야 합니다.")
    private Integer rating;

    // 리뷰 내용입니다.
    @NotBlank(message = "리뷰 내용은 필수입니다.")
    private String content;
}
