package com.example.umc10th.domain.auth.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthErrorCode implements BaseErrorCode {
    // 401 Unauthorized
    LOGIN_FAILED(HttpStatus.UNAUTHORIZED, "AUTH401_1", "아이디 또는 비밀번호가 일치하지 않습니다."),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "AUTH401_2", "토큰이 만료되었습니다."),
    TOKEN_INVALID(HttpStatus.UNAUTHORIZED, "AUTH401_3", "유효하지 않은 토큰입니다."),

    // 403 Forbidden
    FORBIDDEN(HttpStatus.FORBIDDEN, "AUTH403_1", "해당 자원에 대한 접근 권한이 없습니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}