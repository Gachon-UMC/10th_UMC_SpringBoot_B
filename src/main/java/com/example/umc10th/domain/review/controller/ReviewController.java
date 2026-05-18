package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
<<<<<<< Updated upstream
import jakarta.validation.constraints.NotNull;
=======
>>>>>>> Stashed changes
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
<<<<<<< Updated upstream
import org.springframework.web.bind.annotation.*;

// 해당 클래스를 REST API 컨트롤러로 등록합니다.
@RestController
// RequestParam 검증을 활성화합니다.
@Validated
// final 필드를 생성자 주입 방식으로 주입합니다.
@RequiredArgsConstructor
// 리뷰 API의 공통 URL 경로를 지정합니다.
=======
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 리뷰 도메인의 REST API를 처리하는 컨트롤러입니다.
@RestController
@Validated
@RequiredArgsConstructor
>>>>>>> Stashed changes
@RequestMapping("/api/users")
public class ReviewController {

    // 리뷰 관련 비즈니스 로직을 처리하는 서비스입니다.
    private final ReviewService reviewService;

    // 내가 작성한 리뷰 목록을 커서 기반으로 조회합니다.
    @GetMapping("/reviews/my")
    public ResponseEntity<ApiResponse<ReviewResDTO.CursorPagination<ReviewResDTO.MyReviewDTO>>> getMyReviews(
<<<<<<< Updated upstream
            // 요청 쿼리의 userId 값을 메서드 파라미터로 바인딩합니다.
            @RequestParam @NotNull(message = "사용자 ID는 필수입니다.") Long userId,
            // 요청 쿼리의 pageSize 값을 바인딩하고 기본값을 지정합니다.
=======
            // URL에 노출하지 않고 요청 헤더의 userId 값으로 회원을 식별합니다.
            @RequestHeader("userId") Long userId,
            // 요청 쿼리의 pageSize 값을 바인딩하고 검증합니다.
>>>>>>> Stashed changes
            @RequestParam(defaultValue = "3") @Min(value = 1, message = "페이지 크기는 1 이상이어야 합니다.") Integer pageSize,
            // 요청 쿼리의 cursor 값을 바인딩하고 첫 페이지 기본값을 지정합니다.
            @RequestParam(defaultValue = "-1") @Pattern(regexp = "-1|\\d+(\\.\\d+)?:\\d+", message = "커서 형식이 올바르지 않습니다.") String cursor,
            // 요청 쿼리의 query 값을 바인딩하고 ID 정렬 기본값을 지정합니다.
            @RequestParam(defaultValue = "id") @Pattern(regexp = "id|star", message = "정렬 기준은 id 또는 star만 가능합니다.") String query
    ) {
        ReviewResDTO.CursorPagination<ReviewResDTO.MyReviewDTO> response =
                reviewService.getMyReviews(userId, pageSize, cursor, query);

        return ResponseEntity.ok(ApiResponse.onSuccess(ReviewSuccessCode.GET_MY_REVIEWS_SUCCESS, response));
    }

    // 사용자의 리뷰 작성을 처리합니다.
<<<<<<< Updated upstream
    @PostMapping("/{userId}/reviews")
    public ResponseEntity<ApiResponse<ReviewResDTO.CreateReviewDTO>> writeReview(
            // URL 경로의 userId 값을 메서드 파라미터로 바인딩합니다.
            @PathVariable Long userId,
            // 요청 본문 JSON을 리뷰 작성 요청 DTO로 바인딩합니다.
=======
    @PostMapping("/reviews")
    public ResponseEntity<ApiResponse<ReviewResDTO.CreateReviewDTO>> writeReview(
            // URL에 노출하지 않고 요청 헤더의 userId 값으로 회원을 식별합니다.
            @RequestHeader("userId") Long userId,
            // 요청 본문 JSON을 리뷰 작성 요청 DTO로 바인딩하고 검증합니다.
>>>>>>> Stashed changes
            @RequestBody @Valid ReviewReqDTO request
    ) {
        ReviewResDTO.CreateReviewDTO response = reviewService.createReview(userId, request);

        return ResponseEntity.ok(ApiResponse.onSuccess(ReviewSuccessCode.CREATE_REVIEW_SUCCESS, response));
    }
}
