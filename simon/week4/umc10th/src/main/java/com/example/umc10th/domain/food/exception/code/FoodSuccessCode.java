package com.example.umc10th.domain.food.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum FoodSuccessCode implements BaseSuccessCode {
    // 선호 음식 등록 성공
    PREFER_FOOD_CREATED(HttpStatus.CREATED, "FOOD201_1", "선호 음식이 성공적으로 등록되었습니다."),

    // 선호 음식 수정/삭제 성공
    PREFER_FOOD_UPDATED(HttpStatus.OK, "FOOD202_1", "선호 음식 설정이 수정되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
