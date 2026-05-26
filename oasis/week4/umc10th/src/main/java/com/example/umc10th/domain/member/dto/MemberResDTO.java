package com.example.umc10th.domain.member.dto;

import lombok.Builder;

public class MemberResDTO {

    @Builder
    public record LoginResponse(
            String accessToken
    ) {}

    @Builder
    public record SignupResponse(
            Long userId,
            String email,
            String nickname
    ) {}

    @Builder
    public record MypageResponse(
            String nickname,
            String email,
            String phone,
            boolean phoneVerified,
            int point
    ) {}
}
