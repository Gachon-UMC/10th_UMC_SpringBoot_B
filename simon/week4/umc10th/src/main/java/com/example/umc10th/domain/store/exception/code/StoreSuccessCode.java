package com.example.umc10th.domain.store.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum StoreSuccessCode implements BaseSuccessCode {
    // 200 OK
    OK(HttpStatus.OK, "STORE200_1", "성공적으로 가게를 조회했습니다."),
    GET_LIST_OK(HttpStatus.OK, "STORE200_2", "성공적으로 가게 목록을 조회했습니다."),

    // 201 Created
    CREATED(HttpStatus.CREATED, "STORE201_1", "새로운 가게를 성공적으로 등록했습니다."),
    REGION_CREATED(HttpStatus.CREATED, "STORE201_2", "새로운 지역 정보를 등록했습니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
