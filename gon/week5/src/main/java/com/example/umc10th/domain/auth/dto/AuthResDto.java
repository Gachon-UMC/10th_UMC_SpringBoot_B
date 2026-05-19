package com.example.umc10th.domain.auth.dto;

public class AuthResDto {

    public record SignupResult(
            Long userId,
            String email,
            String name
    ){}
}
