package com.example.umc10th.domain.store.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum StoreErrorCode implements BaseErrorCode {

    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE404_1", "존재하지 않는 가게입니다."),
    STORE_REGION_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE404_2", "해당 지역에 존재하는 가게가 없습니다."),
    STORE_CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE404_3", "해당 음식 카테고리에 존재하는 가게가 없습니다."),
    STORE_NAME_ALREADY_EXISTS(HttpStatus.CONFLICT, "STORE409_1", "이미 존재하는 가게 이름입니다."),
    INVALID_STORE_REQUEST(HttpStatus.BAD_REQUEST, "STORE400_1", "잘못된 가게 요청입니다."),
    INVALID_STORE_ID(HttpStatus.BAD_REQUEST, "STORE400_2", "잘못된 가게 ID입니다."),
    ;

    private final HttpStatus Status;
    private final String code;
    private final String message;
}
