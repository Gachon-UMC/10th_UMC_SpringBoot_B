package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class ReviewReqDTO {

    public record CreateReview(
            @NotNull(message="리뷰 작성은 필수입니다.")
            String reviewComment,
            @NotNull(message = "별점 평가는 필수입니다.")
            int star
    ){

    }
}
