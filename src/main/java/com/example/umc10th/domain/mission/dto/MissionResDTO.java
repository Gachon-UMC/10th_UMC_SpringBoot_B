package com.example.umc10th.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MissionResDTO {

    private Long missionId;
    private String storeName;
    private Integer reward;
    private String deadline;
}