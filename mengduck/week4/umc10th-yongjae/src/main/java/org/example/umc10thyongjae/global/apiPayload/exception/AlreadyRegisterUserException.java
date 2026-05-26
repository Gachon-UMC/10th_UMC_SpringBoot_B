package org.example.umc10thyongjae.global.apiPayload.exception;

import org.example.umc10thyongjae.global.apiPayload.code.GeneralErrorCode;

public class AlreadyRegisterUserException extends CommonException {

    public AlreadyRegisterUserException() {
        super(GeneralErrorCode.ALREADY_REGISTER_USER);
    }
}
