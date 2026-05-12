package com.example.umc10th.domain.food.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum FoodErrorCode implements BaseErrorCode {
    // 이미 선호 음식으로 등록된 경우
    PREFER_FOOD_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "FOOD400_1", "이미 선호 음식으로 등록된 카테고리입니다.")
    ,

    // 음식 카테고리를 찾을 수 없을 때
    FOOD_CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "FOOD404_1", "해당하는 음식 카테고리가 존재하지 않습니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
