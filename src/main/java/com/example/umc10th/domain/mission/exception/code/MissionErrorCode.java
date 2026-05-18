package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

// 미션 도메인의 실패 응답 코드를 정의합니다.
@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404", "미션을 찾을 수 없습니다."),
    MEMBER_MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER_MISSION404", "사용자의 미션을 찾을 수 없습니다."),
    MISSION_ALREADY_CHALLENGED(HttpStatus.BAD_REQUEST, "MISSION4001", "이미 도전 중인 미션입니다."),
    MISSION_STATUS_NOT_VALID(HttpStatus.BAD_REQUEST, "MISSION4002", "미션 상태값이 올바르지 않습니다."),
    MISSION_NOT_BELONG_TO_USER(HttpStatus.FORBIDDEN, "MISSION403", "해당 사용자의 미션이 아닙니다.");

    // HTTP 응답 상태입니다.
    private final HttpStatus status;

    // 클라이언트에 전달할 에러 코드입니다.
    private final String code;

    // 클라이언트에 전달할 에러 메시지입니다.
    private final String message;
}
