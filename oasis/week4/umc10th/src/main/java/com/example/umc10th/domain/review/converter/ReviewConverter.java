package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.entity.ReviewImage;
import com.example.umc10th.domain.store.entity.Store;

import java.math.BigDecimal;
import java.util.List;

public class ReviewConverter {

    public static Review toReview(ReviewReqDTO.CreateReviewRequest request, Member member, Store store) {
        return Review.builder()
                .member(member)
                .store(store)
                .rating(BigDecimal.valueOf(request.rating()))
                .content(request.content())
                .build();
    }

    public static ReviewImage toReviewImage(String imageUrl, Review review) {
        return ReviewImage.builder()
                .reviewImgUrl(imageUrl)
                .review(review)
                .build();
    }

    public static List<ReviewImage> toReviewImageList(List<String> imageUrls, Review review) {
        return imageUrls.stream()
                .map(url -> toReviewImage(url, review))
                .toList();
    }

    public static ReviewResDTO.MyReviewListResponse toMyReviewListResponse(List<Review> reviews, boolean hasNext) {
        Long nextCursor = hasNext && !reviews.isEmpty()
                ? reviews.get(reviews.size() - 1).getId()
                : null;

        List<ReviewResDTO.MyReviewDTO> reviewDTOs = reviews.stream()
                .map(ReviewConverter::toMyReviewDTO)
                .toList();

        return ReviewResDTO.MyReviewListResponse.builder()
                .reviews(reviewDTOs)
                .nextCursor(nextCursor)
                .hasNext(hasNext)
                .build();
    }

    public static ReviewResDTO.MyReviewDTO toMyReviewDTO(Review review) {
        return ReviewResDTO.MyReviewDTO.builder()
                .reviewId(review.getId())
                .storeName(review.getStore().getStoreName())
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResDTO.CreateReviewResponse toCreateReviewResponse(Review review) {
        return ReviewResDTO.CreateReviewResponse.builder()
                .reviewId(review.getId())
                .storeId(review.getStore().getId())
                .memberId(review.getMember().getId())
                .rating(review.getRating().intValue())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
