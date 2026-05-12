package com.example.umc10th.domain.mission.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

public class MissionReqDTO {
    // 미션 도전하기
    @Builder
    public record ChallengeMission(
            @NotNull(message = "사용자 ID는 필수입니다.")
            Long memberId,

            @NotNull(message = "미션 ID는 필수입니다.")
            Long missionId
    ) {}

    // 미션 성공 인증
    @Builder
    public record VerifyMission(
            @NotBlank(message = "인증 사진 URL은 필수입니다.")
            String proofImageUrl,

            @NotBlank(message = "인증 내용은 필수입니다.")
            String content
    ) {}

    // 가게 미션 생성
    public record CreateMission(
            @NotNull(message = "마감기한은 필수입니다.")
            LocalDateTime deadline,

            @NotNull(message = "미션 성공 포인트는 필수입니다.")
            @Min(value = 1, message = "포인트는 1점 이상이어야 합니다.")
            Integer point,

            @NotBlank(message = "조건은 빈칸일 수 없습니다.")
            String mission_condition
    ) {}
}
