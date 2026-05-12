package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

// 리뷰 도메인 성공 응답 코드를 정의합니다.
@Getter
// 모든 필드를 받는 생성자를 생성합니다.
@AllArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    CREATE_REVIEW_SUCCESS(HttpStatus.OK, "REVIEW2001", "리뷰 작성에 성공했습니다."),
    GET_MY_REVIEWS_SUCCESS(HttpStatus.OK, "REVIEW2002", "내가 작성한 리뷰 조회에 성공했습니다.");

    // HTTP 응답 상태입니다.
    private final HttpStatus status;

    // 클라이언트에 전달할 성공 코드입니다.
    private final String code;

    // 클라이언트에 전달할 성공 메시지입니다.
    private final String message;
}
