package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.auth.dto.AuthReqDTO;
import com.example.umc10th.domain.auth.dto.AuthResDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.enums.MemberStatus;

import java.time.LocalDateTime;

public class MemberConverter {
    // 조회용
    public static MemberResDTO.GetInfo toGetInfo(Member member) {
        return MemberResDTO.GetInfo.builder()
                .email((member.getEmail()))
                .name(member.getName())
                .point(member.getPoint())
                .phoneNumber(member.getPhoneNumber())
                .profileUrl(member.getProfileUrl())
                .build();
    }

    // 회원가입 요청 DTO -> Member 엔티티 변환
    public static Member toMember(AuthReqDTO.Register dto) {
        return Member.builder()
                .email(dto.email())
                .password(dto.password())
                .name(dto.name())
                .gender(dto.gender())
                .birthDate(dto.birthDate())
                .socialUid(dto.socialUid())
                .socialType(dto.socialType())
                .point(0)
                .memberStatus(MemberStatus.ACTIVE)
                .phoneNumber(dto.phoneNumber())
                .profileUrl(dto.profileUrl())
                .build();
    }

    // Member 엔티티 -> 회원가입 응답 DTO 변환
    public static AuthResDTO.Register toRegisterResDTO(Member member) {
        return AuthResDTO.Register.builder()
                .id(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .createdAt(member.getCreatedAt())
                .build();
    }

    // 엔티티 -> 로그인 응답 변환
    public static AuthResDTO.Login toLoginResDTO(Member member) {
        return AuthResDTO.Login.builder()
                .email(member.getEmail())
                .accessToken("temp-access-token-for-test")      // 나중에 JWT 연동 시 실제 토큰으로 교체
                .refreshToken("temp-refresh-token-for-test")    // 나중에 JWT 연동 시 실제 토큰으로 교체
                .build();
    }

    // 로그아웃 결과 변환
    public static AuthResDTO.LogoutResult toLogoutResultDTO(Long memberId) {
        return AuthResDTO.LogoutResult.builder()
                .memberId(memberId)
                .logoutAt(LocalDateTime.now())
                .build();
    }
}
