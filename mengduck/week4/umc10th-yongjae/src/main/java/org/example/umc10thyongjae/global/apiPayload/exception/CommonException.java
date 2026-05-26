package org.example.umc10thyongjae.global.apiPayload.exception;

import lombok.Getter;
import org.example.umc10thyongjae.global.apiPayload.code.BaseErrorCode;

@Getter
public class CommonException extends RuntimeException {

    private final BaseErrorCode errorCode;

    public CommonException(BaseErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
