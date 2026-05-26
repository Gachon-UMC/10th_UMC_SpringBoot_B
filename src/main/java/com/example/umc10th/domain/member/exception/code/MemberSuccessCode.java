package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

// 회원 도메인 성공 응답 코드를 정의합니다.
@Getter
// 모든 필드를 받는 생성자를 생성합니다.
@AllArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    JOIN_SUCCESS(HttpStatus.OK, "MEMBER2001", "회원가입에 성공했습니다."),
    GET_MEMBER_SUCCESS(HttpStatus.OK, "MEMBER2002", "회원 정보 조회에 성공했습니다."),
    LOGIN_SUCCESS(HttpStatus.OK, "MEMBER2003", "로그인에 성공했습니다.");

    // HTTP 응답 상태입니다.
    private final HttpStatus status;

    // 클라이언트에 전달할 성공 코드입니다.
    private final String code;

    // 클라이언트에 전달할 성공 메시지입니다.
    private final String message;
}
