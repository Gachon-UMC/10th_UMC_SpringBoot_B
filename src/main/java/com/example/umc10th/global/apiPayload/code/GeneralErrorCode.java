package com.example.umc10th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

// 공통 실패 응답 코드를 정의합니다.
@Getter
@AllArgsConstructor
public enum GeneralErrorCode implements BaseErrorCode {

    BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "접근 권한이 없습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 오류입니다.");

    // HTTP 응답 상태입니다.
    private final HttpStatus status;

    // 클라이언트에 전달할 에러 코드입니다.
    private final String code;

    // 클라이언트에 전달할 에러 메시지입니다.
    private final String message;
}
