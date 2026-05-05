package com.example.umc10th.domain.member.dto;

import lombok.Builder;

import java.util.List;

public class HomeResDTO {

    @Builder
    public record HomeResponse(
            int point,
            MissionProgressDTO missionProgress,
            List<MissionDTO> missions
    ) {}

    @Builder
    public record MissionProgressDTO(
            int current,
            int total,
            int rewardPoint
    ) {}

    @Builder
    public record MissionDTO(
            Long id,
            String restaurantName,
            String category,
            String condition,
            int rewardPoint,
            int dDay,
            String status
    ) {}
}
