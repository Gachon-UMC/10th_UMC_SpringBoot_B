package com.example.umc10th.domain.mission.dto;

import lombok.Builder;

public class MissionReqDTO {
    // 미션 도전하기
    @Builder
    public record ChallengeMission(

    ) {}

    // 미션 성공 인증
    @Builder
    public record VerifyMission(
            String proofImageUrl,   // 인증 사진 경로
            String content          // 인증 내용
    ) {}
}
