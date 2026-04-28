package com.example.umc10th.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    @Builder
    public record MissionListResponse(
            List<MissionDTO> missions
    ) {}

    @Builder
    public record MissionDTO(
            Long id,
            String storeName,
            String condition,
            int rewardPoint,
            int dDay,
            String status
    ) {}

    @Builder
    public record MissionCompleteResponse(
            Long userId,
            Long missionId,
            String status,
            LocalDateTime completedAt
    ) {}
}
