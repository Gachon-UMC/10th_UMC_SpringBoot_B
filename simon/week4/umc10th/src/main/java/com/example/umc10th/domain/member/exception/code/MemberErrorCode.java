package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {
    // 400 Bad Request
    INVALID_MEMBER_ID(HttpStatus.BAD_REQUEST, "MEMBER400_1", "유효하지 않은 사용자 ID입니다."),
    MEMBER_STATUS_INACTIVE(HttpStatus.BAD_REQUEST, "MEMBER400_2", "이미 탈퇴했거나 비활성화된 계정입니다."),
    LOGIN_FAILED(HttpStatus.BAD_REQUEST, "MEMBER400_3", "이메일 또는 비밀번호가 일치하지 않습니다."),

    // 404 Not Found
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_1", "해당 사용자를 찾을 수 없습니다."),

    // 409 Conflict
    EMAIL_ALREADY_EXIST(HttpStatus.CONFLICT, "MEMBER409_1", "이미 가입된 이메일입니다."),
    NICKNAME_ALREADY_EXIST(HttpStatus.CONFLICT, "MEMBER409_2", "이미 사용 중인 닉네임입니다."),
    PHONE_ALREADY_EXIST(HttpStatus.CONFLICT, "MEMBER409_3", "이미 등록된 전화번호입니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
