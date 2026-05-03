package com.example.umc._th.domain.mission.exception.code;

import com.example.umc._th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {
    OK(HttpStatus.OK, "MEMBER200_1", "성공적으로 미션 요청을 처리했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
