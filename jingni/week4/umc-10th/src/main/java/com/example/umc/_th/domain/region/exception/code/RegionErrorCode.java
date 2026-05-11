package com.example.umc._th.domain.region.exception.code;

import com.example.umc._th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum RegionErrorCode implements BaseErrorCode {
    REGION_NOT_FOUND(HttpStatus.NOT_FOUND, "REGION404_1", "해당 지역을 찾을 수 없습니다."),
    REGION_MEMBER_REQUEST(HttpStatus.BAD_REQUEST, "REGION400_1", "잘못된 지역 요청입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
