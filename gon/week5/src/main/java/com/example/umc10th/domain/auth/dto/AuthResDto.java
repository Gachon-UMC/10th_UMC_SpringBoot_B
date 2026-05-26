package com.example.umc10th.domain.auth.dto;

import lombok.Builder;

public class AuthResDto {

    public record SignupResult(
            Long userId,
            String email,
            String name
    ){}

    @Builder
    public record LoginResult(
            Long userId,
            String accessToken
    ){}
}
