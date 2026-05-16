package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.auth.dto.AuthReqDTO;
import com.example.umc10th.domain.auth.dto.AuthResDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.enums.MemberStatus;

import java.time.LocalDateTime;

public class MemberConverter {
    // 외부 데이터를 엔티티로 변환 (회원가입 등)
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

    // 엔티티를 조회용 DTO로 변환 (마이페이지 등)
    public static MemberResDTO.GetInfo toGetInfo(Member member) {
        return MemberResDTO.GetInfo.builder()
                .email((member.getEmail()))
                .name(member.getName())
                .point(member.getPoint())
                .phoneNumber(member.getPhoneNumber())
                .profileUrl(member.getProfileUrl())
                .build();
    }

    // 수정 결과 응답 DTO로 변환
    public static MemberResDTO.UpdateInfo toUpdateInfoResDTO(Member member) {
        return MemberResDTO.UpdateInfo.builder()
                .memberId(member.getId())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    // 로그아웃 결과 DTO로 변환
    public static AuthResDTO.LogoutResult toLogoutResultDTO(Long memberId) {
        return AuthResDTO.LogoutResult.builder()
                .memberId(memberId)
                .logoutAt(LocalDateTime.now())
                .build();
    }
}
