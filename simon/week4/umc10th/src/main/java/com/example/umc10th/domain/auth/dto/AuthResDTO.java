package com.example.umc10th.domain.auth.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class AuthResDTO {
    @Builder
    public record Register(
            Long id,
            String email,
            String name,
            LocalDateTime createdAt
    ) {}

    @Builder
    public record Login(
            String email,
            String accessToken,
            String refreshToken
    ) {}

    @Builder
    public record LogoutResult(
            Long memberId,
            LocalDateTime logoutAt
    ) {}
}
