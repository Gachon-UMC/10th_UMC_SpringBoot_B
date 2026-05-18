package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.HomeResponseDTO;
import com.example.umc10th.domain.member.dto.UserJoinRequestDTO;
import com.example.umc10th.domain.member.dto.UserJoinResponseDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.member.enums.SocialType;

// 회원 도메인의 Entity와 DTO 변환을 담당합니다.
public class MemberConverter {

    // 회원가입 요청 DTO를 회원 Entity로 변환합니다.
    public static Member toMember(UserJoinRequestDTO request) {
        return Member.builder()
                .name(request.getNickname())
                .email(request.getEmail())
                .gender(Gender.NONE)
                .address("GANGNAM")
                .socialUid(request.getEmail())
                .socialType(SocialType.GOOGLE)
                .point(0)
                .build();
    }

    // 회원 Entity를 회원가입 응답 DTO로 변환합니다.
    public static UserJoinResponseDTO toUserJoinResponseDTO(Member member) {
        return UserJoinResponseDTO.builder()
                .userId(member.getId())
                .build();
    }

    // 회원 Entity를 홈/마이페이지 응답 DTO로 변환합니다.
    public static HomeResponseDTO toHomeResponseDTO(Member member) {
        return HomeResponseDTO.builder()
                .name(member.getName())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .point(member.getPoint())
                .build();
    }
}
