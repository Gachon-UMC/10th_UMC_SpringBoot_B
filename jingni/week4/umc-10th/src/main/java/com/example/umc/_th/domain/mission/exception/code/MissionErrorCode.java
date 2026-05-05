package com.example.umc._th.domain.mission.exception.code;

import com.example.umc._th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404_1", "해당 미션을 찾을 수 없습니다."),
    MISSION_MEMBER_REQUEST(HttpStatus.BAD_REQUEST, "MISSION400_1", "잘못된 미션 요청입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
