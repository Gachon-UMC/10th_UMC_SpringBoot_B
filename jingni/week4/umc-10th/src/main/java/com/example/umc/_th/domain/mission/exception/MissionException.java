package com.example.umc._th.domain.mission.exception;

import com.example.umc._th.global.apiPayload.code.BaseErrorCode;
import com.example.umc._th.global.apiPayload.exception.ProjectException;

public class MissionException extends ProjectException {
    public MissionException (BaseErrorCode errorCode ){ super(errorCode); }
}
