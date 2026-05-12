package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores")
public class ReviewController {
    private final ReviewService reviewService;

    // 리뷰 작성
    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.CreateReview> createReview(
            @PathVariable(name = "storeId") Long storeId,
            @RequestBody @Valid ReviewReqDTO.CreateReview dto
    ) {
        return ApiResponse.onSuccess(ReviewSuccessCode.CREATE_OK, reviewService.createReview(storeId, dto));
    }

    // 특정 가게 리뷰 조회
    @GetMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.ReviewList> getStoreReviews(
            @PathVariable(name = "storeId") Long storeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ApiResponse.onSuccess(ReviewSuccessCode.GET_OK, reviewService.getStoreReviews(storeId, page, size));
    }

    // 특정 가게의 나의 리뷰 목록 조회 (커서 기반)
    @GetMapping("/{storeId}/reviews/me")
    public ApiResponse<ReviewResDTO.CursorPagination<ReviewResDTO.ReviewItem>> getMyReviews(
            @PathVariable Long storeId,
            @RequestParam Long memberId,
            @RequestParam(required = false) String cursor,
            @RequestParam(defaultValue = "latest") String sortType, // latest 또는 rating
            @RequestParam(defaultValue = "10") Integer size
    ) {
        return ApiResponse.onSuccess(ReviewSuccessCode.MY_REVIEW_OK, reviewService.getMyReviewList(memberId, storeId, cursor, sortType, size));
    }
}
