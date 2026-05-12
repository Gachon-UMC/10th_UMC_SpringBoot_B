package com.example.umc10th.global.exception;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

// 예외에 담긴 에러 코드 조회를 위한 Getter를 생성합니다.
@Getter
// 모든 필드를 받는 생성자를 생성합니다.
@AllArgsConstructor
public class ProjectException extends RuntimeException {

    // 공통 실패 응답을 만들기 위한 에러 코드입니다.
    private final BaseErrorCode errorCode;
}
