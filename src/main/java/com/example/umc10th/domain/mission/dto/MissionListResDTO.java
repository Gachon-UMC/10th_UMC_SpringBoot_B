package com.example.umc10th.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MissionListResDTO {

    private List<MissionDTO> missions;
    private int cursor;
    private int size;

    @Getter
    @AllArgsConstructor
    public static class MissionDTO {
        private Long missionId;
        private String title;
        private String status;
    }
}