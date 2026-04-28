package com.example.umc._th.domain.mission.dto;

import lombok.Builder;

import java.util.List;

public class MissionResDTO {
    public record GetMissions(
        List<MissionDTO> missions
    ){}

    public record GetCompleteMissionsCnt(
        Integer count
    ){}
}

