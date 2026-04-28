package com.example.umc._th.domain.review.dto;

import java.time.LocalDateTime;

public class ReviewResDTO {
    public record CreateReview(
            Long reviewId,
            Long storeId,
            LocalDateTime createdAt
    ){}
}
