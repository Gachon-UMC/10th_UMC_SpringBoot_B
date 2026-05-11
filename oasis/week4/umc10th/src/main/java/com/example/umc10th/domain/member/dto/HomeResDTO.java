package com.example.umc10th.domain.member.dto;

import lombok.Builder;

import java.util.List;

public class HomeResDTO {

    @Builder
    public record HomeResponse(
            String regionName,
            int point,
            MissionProgressDTO missionProgress,
            List<MissionDTO> missions,
            int currentPage,
            int totalPages,
            long totalElements
    ) {}

    @Builder
    public record MissionProgressDTO(
            int current,
            int total,
            int rewardPoint
    ) {}

    @Builder
    public record MissionDTO(
            Long missionId,
            String storeName,
            String category,
            String condition,
            int rewardPoint,
            int dDay
    ) {}
}
