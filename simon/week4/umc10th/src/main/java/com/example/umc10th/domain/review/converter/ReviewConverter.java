package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.entity.ReviewPhoto;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {
    // 커서 기반 페이지네이션 응답 규격 변환
    public static <T> ReviewResDTO.CursorPagination<T> toCursorPagination(List<T> data, String nextCursor, Boolean hasNext) {
        return ReviewResDTO.CursorPagination.<T>builder()
                .data(data)
                .nextCursor(nextCursor)
                .hasNext(hasNext)
                .build();
    }

    // 리뷰 요청 DTO를 DB 저장용 엔티티(Entity)로 변환
    public static Review toReview(ReviewReqDTO.CreateReview request) {
        return Review.builder()
                .content(request.content())
                .score(request.score())
                .reviewPhotoList(new ArrayList<>())
                .replyList(new ArrayList<>())
                .build();
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

    // 리뷰 엔티티 리스트를 개별 리뷰 아이템 DTO(ReviewItem) 리스트로 변환
    public static List<ReviewResDTO.ReviewItem> toReviewItemDTOList(List<Review> reviews) {
        return reviews.stream()
                .map(review -> ReviewResDTO.ReviewItem.builder()
                        .reviewId(review.getId())
                        .content(review.getContent())
                        .score(review.getScore())
                        .reviewerNickname(review.getMember().getName())
                        .createdAt(review.getCreatedAt())
                        .photoUrls(review.getReviewPhotoList().stream()
                                .map(ReviewPhoto::getReviewPhotoUrl)
                                .toList())
                        .build())
                .toList();
    }

    // 오프셋 기반 전체 리뷰 조회 응답 DTO 구성
    public static ReviewResDTO.ReviewList toReviewListDTO(Page<Review> reviewPage) {
        return ReviewResDTO.ReviewList.builder()
                .reviews(toReviewItemDTOList(reviewPage.getContent()))
                .page(reviewPage.getNumber())
                .size(reviewPage.getSize())
                .hasNext(reviewPage.hasNext())
                .build();
    }
}
