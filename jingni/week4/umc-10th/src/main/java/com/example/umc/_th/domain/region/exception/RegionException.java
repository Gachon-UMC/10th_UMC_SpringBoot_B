package com.example.umc._th.domain.region.exception;

import com.example.umc._th.global.apiPayload.code.BaseErrorCode;
import com.example.umc._th.global.apiPayload.exception.ProjectException;

public class RegionException extends ProjectException {
    public RegionException (BaseErrorCode errorCode ){ super(errorCode); }
}
