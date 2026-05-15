package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.dto.CursorResponse;
import com.example.umc10th.global.apiPayload.dto.PageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores")
public class ReviewController {
    private final ReviewService reviewService;

    // 특정 가게 리뷰 조회 (Offset 기반)
    @GetMapping("/{storeId}/reviews")
    public ApiResponse<PageResponse<ReviewResDTO.ReviewItem>> getStoreReviews(
            @PathVariable Long storeId,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return ApiResponse.onSuccess(ReviewSuccessCode.GET_OK, reviewService.getStoreReviews(storeId, pageable));
    }

    // 특정 가게 내 '나의 리뷰' 목록 조회 (Cursor 기반)
    @GetMapping("/{storeId}/reviews/me")
    public ApiResponse<CursorResponse<ReviewResDTO.ReviewItem>> getMyReviews(
            @PathVariable Long storeId,
            @RequestParam Long memberId,
            @RequestParam(required = false) String cursor,
            @RequestParam(defaultValue = "latest") String sortType, // latest 또는 rating
            @RequestParam(defaultValue = "10") Integer size
    ) {
        return ApiResponse.onSuccess(ReviewSuccessCode.MY_REVIEW_OK, reviewService.getMyReviewList(memberId, storeId, cursor, sortType, size));
    }

    // 리뷰 작성
    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.CreateReview> createReview(
            @PathVariable Long storeId,
            @RequestBody @Valid ReviewReqDTO.CreateReview dto
    ) {
        return ApiResponse.onSuccess(ReviewSuccessCode.CREATE_OK, reviewService.createReview(storeId, dto));
    }
}
