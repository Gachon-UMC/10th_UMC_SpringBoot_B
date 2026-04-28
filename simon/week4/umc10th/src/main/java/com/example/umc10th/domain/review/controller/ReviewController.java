package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class ReviewController {
    private final ReviewService reviewService;

    // 리뷰 작성
    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.Create> createReview(
            @PathVariable Long storeId,
            @RequestHeader("Authorization") String accessToken,
            @RequestBody ReviewReqDTO.Create dto
    ) {
        BaseSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, reviewService.createReview(storeId, dto));
    }

    // 특정 가게 리뷰 조회
    @GetMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.ReviewList> getStoreReviews(
            @PathVariable Long storeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        BaseSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, reviewService.getStoreReviews(storeId, page, size));
    }
}
