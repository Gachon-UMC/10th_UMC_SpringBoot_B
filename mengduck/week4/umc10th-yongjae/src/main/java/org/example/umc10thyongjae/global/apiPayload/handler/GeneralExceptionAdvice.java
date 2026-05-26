package org.example.umc10thyongjae.global.apiPayload.handler;

import org.example.umc10thyongjae.global.apiPayload.ApiResponse;
import org.example.umc10thyongjae.global.apiPayload.code.BaseErrorCode;
import org.example.umc10thyongjae.global.apiPayload.code.GeneralErrorCode;
import org.example.umc10thyongjae.global.apiPayload.exception.CommonException;
import org.example.umc10thyongjae.global.apiPayload.exception.NotDataFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GeneralExceptionAdvice {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(Exception ex) {
        BaseErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(code, ex.getMessage()));
    }

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ApiResponse<Void>> handleCommonException(CommonException e) {
        BaseErrorCode errorCode = e.getErrorCode();
        return ResponseEntity.status(errorCode.getStatus())
                .body(ApiResponse.onFailure(errorCode, null));
    }

    @ExceptionHandler(NotDataFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotDataFoundException(NotDataFoundException e){
        BaseErrorCode errorCode = GeneralErrorCode.NOT_FOUND;
        return ResponseEntity.status(errorCode.getStatus())
                .body(ApiResponse.onFailure(errorCode, null));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<String>> handleIllegalArgumentException(IllegalArgumentException e) {
        BaseErrorCode errorCode = GeneralErrorCode.BAD_REQUEST;
        return ResponseEntity.status(errorCode.getStatus())
                .body(ApiResponse.onFailure(errorCode, e.getMessage()));
    }

    /**
     * Catch @RequestBody invalid error
     * */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationException(
            MethodArgumentNotValidException ex
    ) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> {

                    errors.computeIfAbsent(
                            error.getField(),
                            key -> error.getDefaultMessage());

                });

        BaseErrorCode code = GeneralErrorCode.BAD_REQUEST;

        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(code, errors));
    }
}
