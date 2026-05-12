package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

// 미션 도메인 성공 응답 코드를 정의합니다.
@Getter
// 모든 필드를 받는 생성자를 생성합니다.
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    GET_MISSIONS_SUCCESS(HttpStatus.OK, "MISSION2001", "미션 목록 조회에 성공했습니다."),
    CHALLENGE_MISSION_SUCCESS(HttpStatus.OK, "MISSION2002", "미션 도전에 성공했습니다."),
    COMPLETE_MISSION_SUCCESS(HttpStatus.OK, "MISSION2003", "미션 성공 인증에 성공했습니다."),
    GET_IN_PROGRESS_MISSIONS_SUCCESS(HttpStatus.OK, "MISSION2004", "진행중인 미션 조회에 성공했습니다.");

    // HTTP 응답 상태입니다.
    private final HttpStatus status;

    // 클라이언트에 전달할 성공 코드입니다.
    private final String code;

    // 클라이언트에 전달할 성공 메시지입니다.
    private final String message;
}
