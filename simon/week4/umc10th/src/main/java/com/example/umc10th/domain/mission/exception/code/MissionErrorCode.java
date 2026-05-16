package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {
    // 400 Bad Request
    MISSION_ALREADY_CHALLENGING(HttpStatus.BAD_REQUEST, "MISSION400_1", "이미 도전 중인 미션입니다."),
    MISSION_ALREADY_COMPLETED(HttpStatus.BAD_REQUEST, "MISSION400_2", "이미 완료된 미션입니다."),
    MISSION_NOT_CHALLENGING(HttpStatus.BAD_REQUEST, "MISSION400_3", "도전 중인 상태가 아닙니다."),
    MISSION_DEADLINE_EXPIRED(HttpStatus.BAD_REQUEST, "MISSION400_4", "마감 기한이 지난 미션입니다."),
    INVALID_VERIFICATION_CODE(HttpStatus.BAD_REQUEST, "MISSION400_5", "인증 번호가 유효하지 않거나 일치하지 않습니다."),
    MISSION_REGION_MISMATCH(HttpStatus.BAD_REQUEST, "MISSION400_6", "현재 설정된 지역의 미션이 아닙니다."),

    // 403 Forbidden
    MISSION_NOT_OWNER(HttpStatus.FORBIDDEN, "MISSION403_1", "해당 미션에 대한 권한이 없습니다."),

    // 404 Not Found
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404_1", "해당 미션을 찾을 수 없습니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
