package com.example.umc10th.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class MissionResDTO {

    @Builder
    public record MissionListResponse(
            List<MissionDTO> missions,
            int currentPage,
            int totalPages,
            long totalElements
    ) {}

    @Builder
    public record MissionDTO(
            Long memberMissionId,
            Long missionId,
            String storeName,
            String condition,
            int rewardPoint,
            String status
    ) {}

    @Builder
    public record MissionCompleteResponse(
            Long memberMissionId,
            Long missionId,
            String status,
            LocalDate completedAt
    ) {}
}
