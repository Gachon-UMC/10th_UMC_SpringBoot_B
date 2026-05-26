package com.example.umc10th.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

// 로그인 응답 정보를 담는 DTO입니다.
@Getter
@Builder
public class UserLoginResponseDTO {

    // JWT Access Token입니다.
    private String accessToken;
}
