package com.example.umc10th.domain.user.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements BaseErrorCode {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND,"USER404_1", "해당 사용자를 찾을 수 없습니다. "),
    USER_ALREADY_EXISTS(HttpStatus.CONFLICT, "USER409_1", "이미 존재하는 사용자입니다."),
    USER_EMAIL_ALREADY_EXISTS(HttpStatus.CONFLICT, "USER409_2", "이미 사용 중인 이메일입니다."),
    USER_NICKNAME_ALREADY_EXISTS(HttpStatus.CONFLICT, "USER409_3", "이미 사용 중인 닉네임입니다."),
    USER_PASSWORD_NOT_MATCH(HttpStatus.UNAUTHORIZED, "USER401_1", "비밀번호가 일치하지 않습니다."),
    USER_DELETED(HttpStatus.GONE, "USER410_1", "탈퇴한 사용자입니다."),
    ;

    private final HttpStatus Status;
    private final String code;
    private final String message;


}
