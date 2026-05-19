package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import org.springframework.data.domain.Page;

import java.util.List;

public class MissionConverter {

    //페이지네이션 틀
    public static <T> MissionResDTO.OffsetPagination<T> toOffsetPagination(
            List<T> data,
            int page,
            int size
    ) {
        return MissionResDTO.OffsetPagination.<T>builder()
                .data(data)
                .page(page)
                .size(size)
                .build();
    }

    public static <T> MissionResDTO.CursorPagination<T> toCursorPagination(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            int size
    ){
        return MissionResDTO.CursorPagination.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCusor(nextCursor)
                .size(size)
                .build();
    }

    //유저미션
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


    //홈 화면 미션
    public static MissionResDTO.GetHomeMission toGetHomeMission(Mission mission) {
        return MissionResDTO.GetHomeMission.builder()
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
