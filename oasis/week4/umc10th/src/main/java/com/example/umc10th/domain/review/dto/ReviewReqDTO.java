package com.example.umc10th.domain.review.dto;

import java.util.List;

public class ReviewReqDTO {

    public record CreateReviewRequest(
            int rating,
            String content,
            List<String> images
    ) {}
}
