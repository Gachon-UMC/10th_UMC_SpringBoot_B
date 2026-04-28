package com.example.umc._th.domain.mission.dto;

import com.example.umc._th.domain.mission.enums.Status;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {
    public record GetMissions(
        List<MissionDTO> missions
    ){}

    public record GetCompleteMissionsCnt(
        Integer count
    ){}

    public record MissionComplete(
            Long missionId,
            Status status,
            LocalDateTime completedAt
    ){}
}

