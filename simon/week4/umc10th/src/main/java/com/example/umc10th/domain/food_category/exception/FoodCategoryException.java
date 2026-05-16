package com.example.umc10th.domain.food_category.exception;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.apiPayload.exception.ProjectException;

public class FoodCategoryException extends ProjectException {
    public FoodCategoryException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
