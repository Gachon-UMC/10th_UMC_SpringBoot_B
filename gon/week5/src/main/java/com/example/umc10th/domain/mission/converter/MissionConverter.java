package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;

import java.util.List;

public class MissionConverter {

    //유저미션 목록 페이지
    public static MissionResDTO.GetMission toGetMission(UserMission userMission){
        Mission mission = userMission.getMission();
        return MissionResDTO.GetMission.builder()
                .userMissionId((userMission.getId()))
                .missionId(mission.getId())
                .title(mission.getTitle())
                .description(mission.getDescription())
                .point(mission.getPoint())
                .storeId(mission.getStore().getId())
                .storeName(mission.getStore().getStoreName())
                .status(userMission.getStatus())
                .build();

    }

    public static MissionResDTO.GetMissionList toGetMissionList(List<UserMission> userMissions) {
        List<MissionResDTO.GetMission> missions = userMissions.stream()
                .map(MissionConverter::toGetMission)
                .toList();

        return MissionResDTO.GetMissionList.builder()
                .missions(missions)
                .listSize(missions.size())
                .build();
    } //유저가 사용하고 있는 여러 미션들을 한번에 보여주려고 이렇게 list로 묶는
    //방법을 써봤는데 이게 이렇게 하는게 맞는지 잘 모르겠어요
    //아 페이징 쓰면 되는거였네

    //홈 화면 미션 페이지
    public static MissionResDTO.HomeMission toHomeMission(Mission mission) {
        return MissionResDTO.HomeMission.builder()
                .missionId(mission.getId())
                .storeId(mission.getStore().getId())
                .storeName(mission.getStore().getStoreName())
                .foodName(mission.getStore().getFood().getName())
                .title(mission.getTitle())
                .description(mission.getDescription())
                .point(mission.getPoint())
                .build();
    }

    public static MissionResDTO.SuccessMission toSuccessMission(UserMission userMission){
        return MissionResDTO.SuccessMission.builder()
                .userMissionId(userMission.getId())
                .userId(userMission.getId())
                .missionId(userMission.getMission().getId())
                .status(userMission.getStatus())
                .build();
    }
}
