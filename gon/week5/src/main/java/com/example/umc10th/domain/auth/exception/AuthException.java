package com.example.umc10th.domain.auth.exception;

import com.example.umc10th.domain.auth.exception.code.AuthErrorCode;
import com.example.umc10th.global.apiPayload.exception.ProjectException;

public class AuthException extends ProjectException {
    public AuthException(AuthErrorCode errorCode) {
        super(errorCode);

    }
}
