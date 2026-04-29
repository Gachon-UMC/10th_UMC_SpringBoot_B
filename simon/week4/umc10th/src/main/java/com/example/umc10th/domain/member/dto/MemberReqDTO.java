package com.example.umc10th.domain.member.dto;

import lombok.Getter;

public class MemberReqDTO {
    // 마이페이지
    public record GetInfo(
            Long id
    ) {}

    // Request Body 예시
    public record RequestBody(
            String stringTest,
            Long longTest
    ) {}

    // public static class
    @Getter
    public static class RequestBodyClass {
        private String stringTest;
        private Long longTest;
    }

    // 내 정보 수정 시 받을 데이터 (수정 가능한 필드만 넣으세요)
    public record UpdateInfo(
            String name,
            String phoneNumber,
            String profileUrl
    ) {}
}
