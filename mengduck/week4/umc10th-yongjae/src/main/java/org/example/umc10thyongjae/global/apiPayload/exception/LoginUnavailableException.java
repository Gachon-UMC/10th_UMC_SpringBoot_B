package org.example.umc10thyongjae.global.apiPayload.exception;

import org.example.umc10thyongjae.global.apiPayload.code.GeneralErrorCode;

public class LoginUnavailableException extends CommonException {

    public LoginUnavailableException() {
        super(GeneralErrorCode.LOGIN_UNAVAILABLE);
    }
}
