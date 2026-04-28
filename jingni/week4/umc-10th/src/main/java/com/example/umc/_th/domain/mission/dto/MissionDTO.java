package com.example.umc._th.domain.mission.dto;

import com.example.umc._th.domain.mission.enums.Status;
import com.example.umc._th.domain.store.dto.StoreDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MissionDTO {
    public record Mission(
        @JsonProperty("missionId")
        Long id,
        String deadline,
        Integer point,
        Status status,
        StoreDTO.StoreInMission store
    ){}
}
