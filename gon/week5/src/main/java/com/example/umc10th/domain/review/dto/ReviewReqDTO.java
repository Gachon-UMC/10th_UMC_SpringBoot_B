package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public class ReviewReqDTO {

    public record CreateReview(
            @NotNull(message="리뷰 작성은 필수입니다.")
            @Size(min=1, max=600, message="리뷰는 1자 이상 600자 이상 작성하서야 합니다.")
            String reviewComment,
            @NotNull(message = "별점 평가는 필수입니다.")
            @Min(value=1,message="별점은 최소 1점 이상이어야 합니다. ")
            @Max(value=5,message="벌점은 최대 5점입니다.")
            int star
    ){

    }
}
