package com.example.umc10th.domain.member.dto;

public class MemberReqDTO {
    // 마이페이지
    public record GetInfo(
            Long id
    ) {}

    // 내 정보 수정 시 받을 데이터 (수정 가능한 필드만 넣으세요)
    public record UpdateInfo(
            String name,
            String phoneNumber,
            String profileUrl
    ) {}
}
