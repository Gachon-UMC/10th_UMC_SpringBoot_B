package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.member.enums.SocialType;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MemberResDTO {
    @Builder
    public record RequestBody(
            String stringTest,
            Long longTest
    ) {}

    @Builder
    public record Register(
            String email,
            String password,
            String name,
            Gender gender,
            LocalDate birthDate,
            String socialUid,
            SocialType socialType,
            Integer point,
            String phoneNumber,
            String profile_url
    ) {}

    @Builder
    public record Login(
            String email,
            String password
    ) {}

    @Builder
    public record GetInfo(
            String name,
            String profileUrl,
            String email,
            String phoneNumber,
            Integer point
    ) {}

    @Builder
    public record UpdateInfo(
            Long memberId,
            LocalDateTime updatedAt
    ) {}
}
