package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.entity.ReviewPhoto;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {
    // 1. DTO -> Review 엔티티 변환
    public static Review toReview(ReviewReqDTO.CreateReview request) {
        return Review.builder()
                .content(request.content())
                .score(request.score())
                .build();
    }

    // 2. 사진 URL 리스트 -> ReviewPhoto 엔티티 리스트 변환
    public static List<ReviewPhoto> toReviewPhotoList(List<String> photoUrls) {
        if (photoUrls == null) return List.of();

        return photoUrls.stream()
                .map(url -> ReviewPhoto.builder()
                        .reviewPhotoUrl(url)
                        .build())
                .collect(Collectors.toList());
    }

    // 3. 결과 반환용 DTO 변환 (CreateReviewResult 등)
    public static ReviewResDTO.CreateReview toCreateReviewResult(Review review) {
        return ReviewResDTO.CreateReview.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
