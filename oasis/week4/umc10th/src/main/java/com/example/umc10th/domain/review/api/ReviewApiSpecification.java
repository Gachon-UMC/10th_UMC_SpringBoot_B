package com.example.umc10th.domain.review.api;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Review", description = "리뷰 관련 API")
public interface ReviewApiSpecification {

    @Operation(summary = "리뷰 작성", description = "특정 가게에 리뷰를 작성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "리뷰 작성 성공"),
            @ApiResponse(responseCode = "404", description = "회원 또는 가게를 찾을 수 없음")
    })
    ResponseEntity<com.example.umc10th.global.apiPayload.ApiResponse<ReviewResDTO.CreateReviewResponse>> createReview(
            @Parameter(description = "가게 ID", example = "1") @PathVariable Long storeId,
            @RequestBody ReviewReqDTO.CreateReviewRequest request
    );

    @Operation(summary = "내가 작성한 리뷰 조회", description = "Request Body로 사용자 ID를 받아 내가 작성한 리뷰를 커서 기반 페이지네이션으로 조회합니다. sortBy=id (ID순) 또는 sortBy=rating (별점순)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "내 리뷰 목록 조회 성공"),
            @ApiResponse(responseCode = "404", description = "회원을 찾을 수 없음")
    })
    ResponseEntity<com.example.umc10th.global.apiPayload.ApiResponse<ReviewResDTO.MyReviewListResponse>> getMyReviews(
            @RequestBody ReviewReqDTO.MyReviewRequest request,
            @Parameter(description = "커서 (마지막 리뷰 ID, 첫 페이지는 생략)") @RequestParam(required = false) Long cursor,
            @Parameter(description = "페이지 크기", example = "10") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "정렬 기준 (id/rating)", example = "id") @RequestParam(defaultValue = "id") String sortBy
    );
}
