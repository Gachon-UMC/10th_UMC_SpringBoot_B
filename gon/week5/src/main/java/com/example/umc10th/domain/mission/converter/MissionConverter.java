package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.UserMission;

public class MissionConverter {

    public static MissionResDTO.GetMission toGetMission(Mission mission){
        return MissionResDTO.GetMission.builder()
                .missionId(mission.getId())
                .title(mission.getTitle())
                .description(mission.getDescription())
                .point(mission.getPoint())
                .storeId(mission.getStore().getId())
                .build();

    }

    public static MissionResDTO.SuccessMission toSuccessMission(UserMission userMission){
        return MissionResDTO.SuccessMission.builder()
                .userMissionId(userMission.getId())
                .userId(userMission.getId())
                .missionId(userMission.getMission().getId())
                .status(userMission.getStatus().name())
                .build();
    }
}
