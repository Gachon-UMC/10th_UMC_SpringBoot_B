package com.example.umc._th.domain.mission.dto;

import com.example.umc._th.domain.mission.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {
    @Schema(name = "GetMissionsResponse")
    public record GetMissions(
        List<MissionDTO> missions
    ){}

    @Schema(name = "GetCompleteMissionsCntResponse")
    public record GetCompleteMissionsCnt(
        Integer count
    ){}

    @Schema(name = "MissionCompleteResponse")
    public record MissionComplete(
            Long missionId,
            Status status,
            LocalDateTime completedAt
    ){}
}

