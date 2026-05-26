package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    MEMBER_SIGNUP_SUCCESS(HttpStatus.CREATED, "MEMBER201_1", "회원가입이 성공적으로 완료되었습니다."),
    MEMBER_LOGIN_SUCCESS(HttpStatus.OK, "MEMBER200_3", "로그인에 성공했습니다."),
    MEMBER_HOME_SUCCESS(HttpStatus.OK, "MEMBER200_1", "홈 화면 조회에 성공했습니다."),
    MEMBER_MYPAGE_SUCCESS(HttpStatus.OK, "MEMBER200_2", "마이페이지 조회에 성공했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
