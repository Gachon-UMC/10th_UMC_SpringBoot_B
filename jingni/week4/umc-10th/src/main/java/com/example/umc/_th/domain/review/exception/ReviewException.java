package com.example.umc._th.domain.review.exception;

import com.example.umc._th.global.apiPayload.code.BaseErrorCode;
import com.example.umc._th.global.apiPayload.exception.ProjectException;

public class ReviewException extends ProjectException {
    public ReviewException (BaseErrorCode errorCode ){ super(errorCode); }
}
