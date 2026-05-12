package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

// 리뷰 도메인 실패 응답 코드를 정의합니다.
@Getter
// 모든 필드를 받는 생성자를 생성합니다.
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404", "리뷰를 찾을 수 없습니다."),
    QUERY_NOT_VALID(HttpStatus.BAD_REQUEST, "REVIEW4001", "지원하지 않는 리뷰 정렬 기준입니다."),
    CURSOR_NOT_VALID(HttpStatus.BAD_REQUEST, "REVIEW4002", "올바르지 않은 커서 형식입니다.");

    // HTTP 응답 상태입니다.
    private final HttpStatus status;

    // 클라이언트에 전달할 에러 코드입니다.
    private final String code;

    // 클라이언트에 전달할 에러 메시지입니다.
    private final String message;
}
