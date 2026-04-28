package com.example.umc._th.domain.member.exception.code;

import com.example.umc._th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {
    OK(HttpStatus.OK, "MEMBER200_1", "성공적으로 사용자 요청을 처리했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
