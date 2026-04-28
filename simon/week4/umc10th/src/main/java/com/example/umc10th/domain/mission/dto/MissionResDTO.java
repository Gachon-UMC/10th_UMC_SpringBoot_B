package com.example.umc10th.domain.mission.dto;

import lombok.Builder;

import java.util.List;

public class MissionResDTO {
    @Builder
    public record MissionItem(
            Long missionId,
            String title,
            String reward,
            String createdAt
    ) {}

    @Builder
    public record MissionList(
            List<MissionItem> missions,
            Integer page,
            Integer size,
            Boolean hasNext
    ) {}

    @Builder
    public record Challenge(
            Long missionId,
            String message
    ) {}
}
