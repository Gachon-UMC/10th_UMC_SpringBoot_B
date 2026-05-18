package com.example.umc10th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

// 공통 실패 응답 코드 필드 조회를 위한 Getter를 생성합니다.
@Getter
// 모든 필드를 받는 생성자를 생성합니다.
@AllArgsConstructor
public enum GeneralErrorCode implements BaseErrorCode {

    BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 오류입니다.");

    // HTTP 응답 상태입니다.
    private final HttpStatus status;

    // 클라이언트에 전달할 에러 코드입니다.
    private final String code;

    // 클라이언트에 전달할 에러 메시지입니다.
    private final String message;
}
