package com.example.umc10th.domain.review.dto;

import lombok.Builder;

import java.util.List;

public class ReviewReqDTO {
    @Builder
    public record Create(
            String content,
            Float score,
            List<String> photoUrls
    ) {}
}
