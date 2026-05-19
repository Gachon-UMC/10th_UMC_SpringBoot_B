package com.example.umc10th.domain.auth.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.apiPayload.exception.ProjectException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AuthErrorCode implements BaseErrorCode {
    REQUIRED_TERMS_AGREED(HttpStatus.BAD_REQUEST, "AUTH400_1","필수 약관에 동의해야합니다");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
