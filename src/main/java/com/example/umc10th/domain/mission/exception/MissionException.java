package com.example.umc10th.domain.mission.exception;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.exception.ProjectException;

// 미션 도메인에서 발생하는 비즈니스 예외입니다.
public class MissionException extends ProjectException {

    // 미션 도메인 에러 코드를 공통 예외 형식으로 전달합니다.
    public MissionException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
