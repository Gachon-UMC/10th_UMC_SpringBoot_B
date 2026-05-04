package com.example.umc10th.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MissionCompleteResDTO {

    private Long userMissionId;
    private String status;
}