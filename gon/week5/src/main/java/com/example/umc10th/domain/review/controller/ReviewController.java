package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.domain.review.service.ReviewServiceImpl;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class ReviewController {

    private final ReviewService reviewService;

    //작성한 리뷰 조회
    @GetMapping("/users/{userId}/reviews")
    public ApiResponse<ReviewResDTO.CursorPagination<ReviewResDTO.GetReview>> getReviews(
            @PathVariable Long userId,
            @RequestParam int size,
            @RequestParam String cursor,
            @RequestParam String query
    ){
        BaseSuccessCode code = ReviewSuccessCode.OK;
        return ApiResponse.onSuccess(code,reviewService.getReviews(userId,size,cursor,query));
    }


    @PostMapping("/stores/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.CreateReview> createReview(
            @PathVariable Long storeId,
            @RequestParam Long userId,
            @RequestBody @Valid ReviewReqDTO.CreateReview request

    ){
        BaseSuccessCode code = ReviewSuccessCode.OK;
        return ApiResponse.onSuccess(code,reviewService.createReview(
                storeId,userId,request));
    }


}
