package com.example.umc10th.domain.term.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum TermSuccessCode implements BaseSuccessCode {
    // 200 OK
    TERM_LIST_FETCHED(HttpStatus.OK, "TERM200_1", "약관 목록을 성공적으로 조회했습니다."),
    TERM_DETAIL_FETCHED(HttpStatus.OK, "TERM200_2", "약관 상세 내용을 성공적으로 조회했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
