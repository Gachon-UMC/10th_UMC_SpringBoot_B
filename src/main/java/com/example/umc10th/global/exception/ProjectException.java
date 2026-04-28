package com.example.umc10th.global.exception;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProjectException extends RuntimeException {

    private final BaseErrorCode errorCode;
}