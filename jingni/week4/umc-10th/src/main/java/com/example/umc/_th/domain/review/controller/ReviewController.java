package com.example.umc._th.domain.review.controller;

import com.example.umc._th.domain.review.dto.ReviewReqDTO;
import com.example.umc._th.domain.review.dto.ReviewResDTO;
import com.example.umc._th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc._th.domain.review.service.ReviewService;
import com.example.umc._th.global.apiPayload.ApiResponse;
import com.example.umc._th.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/v1/stores/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.CreateReview> createReview(
            @PathVariable("storeId")  Long storeId,
            @RequestBody @Valid ReviewReqDTO.CreateReview dto
            ){
        Long memberId = 1L;
        BaseSuccessCode code = ReviewSuccessCode.CREATED;
        return ApiResponse.onSuccess(code, reviewService.createReview(storeId, memberId, dto));
    }
}
