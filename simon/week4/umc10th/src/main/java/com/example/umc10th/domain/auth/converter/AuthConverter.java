package com.example.umc10th.domain.auth.converter;

import com.example.umc10th.domain.auth.dto.AuthResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.member.enums.MemberStatus;
import com.example.umc10th.global.security.dto.OAuthDTO;

import java.time.LocalDate;

import static java.util.UUID.randomUUID;

public class AuthConverter {
    // 소셜 로그인 유저 정보를 기반으로 신규 Member 엔티티 빌드
    public static Member toMember(OAuthDTO dto) {
        return Member.builder()
                .email(dto.getSocialEmail())
                .name(dto.getName())
                .socialUid(dto.getSocialUid())
                .socialType(dto.getSocialType())
                // 소셜 유저는 패스워드가 필요 없으므로 UUID 무작위 해시값으로 암호화 대치
                .password(randomUUID().toString())
                .point(0)
                .memberStatus(MemberStatus.ACTIVE)
                .gender(Gender.NONE)
                .birthDate(LocalDate.now())
                .address("카카오 소셜 유저")
                .phoneNumber(null)
                .profileUrl(null)
                .build();
    }

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
    public static AuthResDTO.Login toLoginResDTO(Member member, String accessToken, String refreshToken) {
        return AuthResDTO.Login.builder()
                .email(member.getEmail())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
