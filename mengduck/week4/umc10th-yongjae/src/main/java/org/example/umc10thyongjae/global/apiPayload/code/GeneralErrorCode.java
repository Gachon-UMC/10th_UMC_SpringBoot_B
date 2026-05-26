package org.example.umc10thyongjae.global.apiPayload.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum GeneralErrorCode implements BaseErrorCode {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "BAD_REQUEST", "잘못된 요청입니다. "),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED", "인증되지 않았습니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "FORBIDDEN", "접근이 금지되었습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "NOT_FOUND", "해당 리소스를 찾을 수 없습니다."),
    ALREADY_REGISTER_USER(HttpStatus.CONFLICT, "ALREADY_REGISTER_USER", "이미 가입된 사용자입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", "서버 에러가 발생했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
