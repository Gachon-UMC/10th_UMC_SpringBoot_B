package org.example.umc10thyongjae.global.apiPayload.handler;

import org.example.umc10thyongjae.global.apiPayload.ApiResponse;
import org.example.umc10thyongjae.global.apiPayload.code.BaseErrorCode;
import org.example.umc10thyongjae.global.apiPayload.code.GeneralErrorCode;
import org.example.umc10thyongjae.global.apiPayload.exception.NotDataFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionAdvice {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(Exception ex) {
        BaseErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(code, ex.getMessage()));
    }

    @ExceptionHandler(NotDataFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotDataFoundException(NotDataFoundException e){
        BaseErrorCode errorCode = GeneralErrorCode.NOT_FOUND;
        return ResponseEntity.status(errorCode.getStatus())
                .body(ApiResponse.onFailure(errorCode, null));
    }
}
