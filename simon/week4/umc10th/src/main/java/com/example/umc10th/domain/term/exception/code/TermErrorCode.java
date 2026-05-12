package com.example.umc10th.domain.term.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum TermErrorCode implements BaseErrorCode {
    // 400 Bad Request
    REQUIRED_TERM_NOT_AGREED(HttpStatus.BAD_REQUEST, "TERM400_1", "필수 약관에 동의하지 않았습니다."),

    // 404 Not Found
    TERM_NOT_FOUND(HttpStatus.NOT_FOUND, "TERM404_1", "존재하지 않는 약관입니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
