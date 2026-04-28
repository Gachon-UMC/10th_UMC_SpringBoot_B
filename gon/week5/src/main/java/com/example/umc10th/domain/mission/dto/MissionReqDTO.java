package com.example.umc10th.domain.mission.dto;

import lombok.Builder;

public class MissionReqDTO {

    @Builder
    public record GetMission(
            Long missionId,
            String title,
            String description,
            Integer point,
            Long storeId
    ){

    }
}
