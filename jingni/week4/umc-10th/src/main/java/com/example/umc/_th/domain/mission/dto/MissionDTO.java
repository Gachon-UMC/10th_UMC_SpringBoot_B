package com.example.umc._th.domain.mission.dto;

import com.example.umc._th.domain.mission.enums.Status;
import com.example.umc._th.domain.store.dto.StoreDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class MissionDTO {
    public record Mission(
        @JsonProperty("missionId")
        Long id,
        LocalDate deadline,
        Integer point,
        Status status,
        StoreDTO.StoreSummary store
    ){}
}
