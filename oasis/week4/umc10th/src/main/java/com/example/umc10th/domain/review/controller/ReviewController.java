package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.api.ReviewApiSpecification;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController implements ReviewApiSpecification {

    private final ReviewService reviewService;

    @PostMapping("/stores/{storeId}/reviews")
    public ResponseEntity<ApiResponse<ReviewResDTO.CreateReviewResponse>> createReview(
            @PathVariable Long storeId,
            @Valid @RequestBody ReviewReqDTO.CreateReviewRequest request
    ) {
        ReviewResDTO.CreateReviewResponse response = reviewService.createReview(request);
        return ResponseEntity
                .status(ReviewSuccessCode.REVIEW_CREATE_SUCCESS.getStatus())
                .body(ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_CREATE_SUCCESS, response));
    }

    @PostMapping("/reviews/my")
    public ResponseEntity<ApiResponse<ReviewResDTO.MyReviewListResponse>> getMyReviews(
            @Valid @RequestBody ReviewReqDTO.MyReviewRequest request,
            @RequestParam(required = false) Long cursor,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        ReviewResDTO.MyReviewListResponse response = reviewService.getMyReviews(
                request.memberId(), cursor, size, sortBy);
        return ResponseEntity
                .status(ReviewSuccessCode.MY_REVIEW_LIST_SUCCESS.getStatus())
                .body(ApiResponse.onSuccess(ReviewSuccessCode.MY_REVIEW_LIST_SUCCESS, response));
    }
}
