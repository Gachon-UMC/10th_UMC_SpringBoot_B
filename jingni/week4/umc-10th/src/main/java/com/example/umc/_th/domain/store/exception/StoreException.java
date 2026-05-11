package com.example.umc._th.domain.store.exception;

import com.example.umc._th.global.apiPayload.code.BaseErrorCode;
import com.example.umc._th.global.apiPayload.exception.ProjectException;

public class StoreException extends ProjectException {
    public StoreException (BaseErrorCode errorCode ){ super(errorCode); }
}
