package org.example.umc10thyongjae.global.apiPayload.exception;

public class NotDataFoundException extends RuntimeException {

    public NotDataFoundException(String dataName) {
        super(dataName + " is not found.");
    }
}
