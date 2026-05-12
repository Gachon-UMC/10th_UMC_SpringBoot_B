package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {
    // 리뷰 작성 성공
    CREATE_OK(HttpStatus.OK, "REVIEW200_1", "리뷰를 성공적으로 등록했습니다."),

    // 리뷰 조회 성공
    GET_OK(HttpStatus.OK, "REVIEW200_2", "성공적으로 리뷰를 조회했습니다."),

    // 커서 기반 조회 성공
    MY_REVIEW_OK(HttpStatus.OK, "REVIEW200_3", "나의 리뷰 목록을 성공적으로 조회했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}