package com.example.umc._th.domain.member.converter;

import com.example.umc._th.domain.member.dto.MemberReqDTO;
import com.example.umc._th.domain.member.dto.MemberResDTO;
import com.example.umc._th.domain.member.entity.Member;

public class MemberConverter {

    public static MemberResDTO.GetInfo toGetInfo(Member member) {
        return MemberResDTO.GetInfo.builder()
                .name(member.getName())
                .email(member.getEmail())
                .phoneNum(member.getPhoneNum())
                .point(member.getPoint())
                .birth(member.getBirth())
                .address(member.getAddress())
                .build();
    }

    public static Member toEntity(MemberReqDTO.Signup request) {
        return Member.builder()
                .name(request.name())
                .email(request.email())
                .phoneNum(request.phoneNum())
                .gender(request.gender())
                .birth(request.birth())
                .address(request.address())
                .socialType(request.socialType())
                .build();
    }

    public static MemberResDTO.Signup toSignup(Member member) {
        return new MemberResDTO.Signup(member.getId());
    }
}