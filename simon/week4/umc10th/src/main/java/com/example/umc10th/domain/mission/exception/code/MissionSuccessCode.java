package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {
    // 200 OK
    OK(HttpStatus.OK, "MISSION200_1", "성공적으로 미션을 조회했습니다."),
    MISSION_VERIFIED(HttpStatus.OK, "MISSION200_2", "8자리 인증 번호가 발급되었습니다. 사장님께 보여드리세요."),

    // 201 Created
    CREATED(HttpStatus.CREATED, "MISSION201_1", "미션 생성을 완료했습니다."),
    MISSION_STARTED(HttpStatus.CREATED, "MISSION201_2", "미션 도전을 시작했습니다."),
    MISSION_COMPLETED(HttpStatus.OK, "MISSION203", "미션 완료 및 포인트 지급 완료")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}