package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {
    // 400 Bad Request
    INVALID_REVIEW_PARAMETER(HttpStatus.BAD_REQUEST, "REVIEW400_1", "리뷰 요청 파라미터가 잘못되었습니다."),
    INVALID_CURSOR(HttpStatus.BAD_REQUEST, "REVIEW400_2", "잘못된 커서 형식입니다."),
    INVALID_RATING(HttpStatus.BAD_REQUEST, "REVIEW400_3", "별점은 0점에서 5점 사이여야 합니다."),

    // 403 Forbidden
    NOT_REVIEW_OWNER(HttpStatus.FORBIDDEN, "REVIEW403_1", "해당 리뷰를 수정/삭제할 권한이 없습니다."),

    // 404 Not Found
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404_1", "해당 리뷰를 찾을 수 없습니다."),
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404_2", "존재하지 않는 가게입니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404_3", "존재하지 않는 사용자입니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
