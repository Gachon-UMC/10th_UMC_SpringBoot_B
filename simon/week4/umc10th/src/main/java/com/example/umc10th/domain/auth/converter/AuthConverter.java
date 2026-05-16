package com.example.umc10th.domain.auth.converter;

import com.example.umc10th.domain.auth.dto.AuthResDTO;
import com.example.umc10th.domain.member.entity.Member;

public class AuthConverter {
    // 회원가입 응답 변환
    public static AuthResDTO.Register toRegisterResDTO(Member member) {
        return AuthResDTO.Register.builder()
                .id(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .createdAt(member.getCreatedAt())
                .build();
    }

    // 로그인 응답 변환
    public static AuthResDTO.Login toLoginResDTO(Member member) {
        return AuthResDTO.Login.builder()
                .email(member.getEmail())
                .accessToken("temp-token")
                .refreshToken("temp-refresh")
                .build();
    }
}
