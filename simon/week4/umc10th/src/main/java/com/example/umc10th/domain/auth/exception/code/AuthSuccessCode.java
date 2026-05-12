package com.example.umc10th.domain.auth.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthSuccessCode implements BaseSuccessCode {
    // 로그인 성공
    LOGIN_SUCCESS(HttpStatus.OK,"AUTH200_1", "성공적으로 로그인했습니다."),

    // 로그아웃 성공
    LOGOUT_SUCCESS(HttpStatus.OK,"AUTH200_2", "성공적으로 로그아웃했습니다."),

    // 토큰 재발급 성공
    TOKEN_REISSUE(HttpStatus.OK, "AUTH200_3", "토큰이 재발급되었습니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
