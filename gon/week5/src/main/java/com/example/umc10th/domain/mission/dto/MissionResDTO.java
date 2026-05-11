package com.example.umc10th.domain.mission.dto;

import lombok.Builder;

public class MissionResDTO {
    @Builder
    public record GetMission(
            Long missionId,
            String title,
            String description,
            Integer point,
            Long storeId
    ){

    }

    @Builder
    public record SuccessMission(
            Long userMissionId,
            Long userId,
            Long missionId,
            String status
    ){

    }
}
