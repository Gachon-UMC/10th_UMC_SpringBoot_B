package com.example.umc10th.domain.member.dto;

import lombok.Builder;

public class MemberResDTO {

    @Builder
    public record SignupResponse(
            Long userId,
            String email,
            String nickname
    ) {}

    @Builder
    public record MypageResponse(
            String profileImage,
            String nickname,
            String email,
            String phoneNumber,
            int point
    ) {}
}
