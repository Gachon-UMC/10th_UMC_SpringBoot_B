package com.example.umc._th.domain.member.exception;

import com.example.umc._th.global.apiPayload.code.BaseErrorCode;
import com.example.umc._th.global.apiPayload.exception.ProjectException;

public class MemberException extends ProjectException {
    public MemberException (BaseErrorCode errorCode ){ super(errorCode); }
}
