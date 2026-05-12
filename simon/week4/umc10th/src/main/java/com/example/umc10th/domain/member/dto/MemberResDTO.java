package com.example.umc10th.domain.member.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class MemberResDTO {
    @Builder
    public record GetInfo(
            String email,
            String name,
            String phoneNumber,
            Integer point,
            String profileUrl
    ) {}

    @Builder
    public record UpdateInfo(
            Long memberId,
            LocalDateTime updatedAt
    ) {}
}
