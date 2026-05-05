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
}
