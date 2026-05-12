package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {
    // 사용자 조회
    OK(HttpStatus.OK, "MEMBER200_1", "성공적으로 사용자를 조회했습니다."),

    // 수정 성공
    UPDATED(HttpStatus.OK, "MEMBER200_2", "성공적으로 사용자 정보를 수정했습니다."),

    // 삭제 성공 (회원 탈퇴)
    DELETED(HttpStatus.OK, "MEMBER200_3", "성공적으로 탈퇴 처리가 완료되었습니다."),

    // 생성 성공
    CREATED(HttpStatus.CREATED, "MEMBER201_1", "성공적으로 사용자를 생성했습니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
