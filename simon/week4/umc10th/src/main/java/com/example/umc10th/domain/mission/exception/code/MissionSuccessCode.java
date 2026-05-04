package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {
    OK(HttpStatus.OK,
            "MISSION200_1",
            "미션 요청 처리 성공")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}