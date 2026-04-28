package com.example.umc._th.domain.mission.dto;

import lombok.Builder;

import java.util.ArrayList;

public class MissionResDTO {
    @Builder
    public record GetMissions(
        ArrayList<MissionDTO> missions
    ){}
}
