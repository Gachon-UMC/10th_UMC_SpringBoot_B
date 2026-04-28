package com.example.umc._th.domain.review.dto;

public class ReviewReqDTO {
    public record CreateReview(
            String content,
            Float star
    ){}
}
