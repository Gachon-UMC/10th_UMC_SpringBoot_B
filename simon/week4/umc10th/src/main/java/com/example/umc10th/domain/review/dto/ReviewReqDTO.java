package com.example.umc10th.domain.review.dto;

import lombok.Builder;

import java.util.List;

public class ReviewReqDTO {
    @Builder
    public record CreateReview(
            String content,
            Float score,
            Long memberId,
            List<String> photoUrls
    ) {}
}
