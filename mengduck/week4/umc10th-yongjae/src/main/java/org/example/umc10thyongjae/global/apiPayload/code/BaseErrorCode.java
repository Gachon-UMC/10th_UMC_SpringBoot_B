package org.example.umc10thyongjae.global.apiPayload.code;

import org.springframework.http.HttpStatus;

public interface BaseErrorCode {

    HttpStatus getStatus();

    String getCode();

    String getMessage();
}
