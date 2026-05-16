package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.entity.ReviewPhoto;
import com.example.umc10th.global.apiPayload.dto.CursorResponse;
import com.example.umc10th.global.apiPayload.dto.PageResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    // 번호 기반 페이징 변환
    public static PageResponse<ReviewResDTO.ReviewItem> toReviewPageDTO(Page<Review> reviewPage) {
        // 엔티티 리스트를 DTO 리스트로 먼저 변환
        List<ReviewResDTO.ReviewItem> data = toReviewItemDTOList(reviewPage.getContent());
        return PageResponse.of(reviewPage, data);
    }

     // 커서 기반 페이징 변환
    public static <T> CursorResponse<T> toCursorResponse(List<T> data, String nextCursor, Boolean hasNext) {
        return CursorResponse.of(data, nextCursor, hasNext);
    }

    // 단일 항목 변환 메서드
    public static ReviewResDTO.ReviewItem toReviewItem(Review review) {
        return ReviewResDTO.ReviewItem.builder()
                .reviewId(review.getId())
                .content(review.getContent())
                .score(review.getScore())
                .reviewerNickname(review.getMember().getName())
                .createdAt(review.getCreatedAt())
                .photoUrls(review.getReviewPhotoList().stream()
                        .map(ReviewPhoto::getReviewPhotoUrl)
                        .toList())
                .build();
    }

    // 리뷰 엔티티 리스트를 개별 리뷰 아이템 DTO(ReviewItem) 리스트로 변환
    public static List<ReviewResDTO.ReviewItem> toReviewItemDTOList(List<Review> reviews) {
        return reviews.stream().map(ReviewConverter::toReviewItem).toList();
    }

    // 리뷰 요청 DTO를 DB 저장용 엔티티(Entity)로 변환
    public static Review toReview(ReviewReqDTO.CreateReview request) {
        return Review.of(request);
    }

    // 리뷰 사진 URL 리스트를 사진 엔티티 리스트로 변환
    public static List<ReviewPhoto> toReviewPhotoList(List<String> photoUrls) {
        if (photoUrls == null) return List.of();

        return photoUrls.stream()
                .map(url -> ReviewPhoto.builder()
                        .reviewPhotoUrl(url)
                        .build())
                .collect(Collectors.toList());
    }

    // 리뷰 생성 후 반환할 결과 DTO 구성
    public static ReviewResDTO.CreateReview toCreateReviewResult(Review review) {
        return ReviewResDTO.CreateReview.builder()
                .reviewId(review.getId())
                .message("리뷰가 성공적으로 등록되었습니다.")
                .createdAt(review.getCreatedAt())
                .build();
    }
}
