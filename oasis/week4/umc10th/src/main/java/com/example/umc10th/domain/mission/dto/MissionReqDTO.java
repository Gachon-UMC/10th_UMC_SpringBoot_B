package com.example.umc10th.domain.mission.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class MissionReqDTO {

    @Schema(description = "내 진행중인 미션 조회 요청")
    public record MyMissionRequest(
            @NotNull(message = "회원 ID는 필수입니다.")
            @Schema(description = "회원 ID", example = "1")
            Long memberId
    ) {}
}
