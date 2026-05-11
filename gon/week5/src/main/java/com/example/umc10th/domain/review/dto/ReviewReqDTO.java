package com.example.umc10th.domain.review.dto;

import lombok.Getter;

public class ReviewReqDTO {

    public record CreateReview(
            String reviewComment,
            int star
    ){

    }
}
