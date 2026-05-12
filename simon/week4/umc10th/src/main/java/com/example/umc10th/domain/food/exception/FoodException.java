package com.example.umc10th.domain.food.exception;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.apiPayload.exception.ProjectException;

public class FoodException extends ProjectException {
    public FoodException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
