package com.example.umc10th.domain.store.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum StoreErrorCode implements BaseErrorCode {
    // 400 Bad Request
    INVALID_STORE_ID(HttpStatus.BAD_REQUEST, "STORE400_1", "잘못된 가게 식별자입니다."),
    INVALID_REGION_ID(HttpStatus.BAD_REQUEST, "STORE400_2", "잘못된 지역 식별자입니다."),

    // 404 Not Found
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE404_1", "해당 가게를 찾을 수 없습니다."),
    REGION_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE404_2", "존재하지 않는 지역 정보입니다."),
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE404_3", "존재하지 않는 음식 카테고리입니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
