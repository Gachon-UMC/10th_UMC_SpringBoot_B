package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.enums.UserMissionStatus;
import com.example.umc10th.domain.user.enums.FoodName;
import lombok.Builder;

import java.util.List;

public class MissionResDTO {
    @Builder
    public record GetMission(
            Long missionId,
            Long userMissionId,
            String storeName,
            String title,
            String description,
            Integer point,
            Long storeId,
            UserMissionStatus status
    ){

    }

    //내가 도전 중인 미션
    @Builder
    public record GetMissionList(
            List<GetMission> missions,
            int listSize
    ) {
    }

    //홈 미션 화면
    @Builder
    public record HomeMission(
            Long missionId,
            Long storeId,
            String storeName,
            FoodName foodName,
            String title,
            String description,
            int point
    ){

    }
    //미션 성공으로 바꾸기
    @Builder
    public record SuccessMission(
            Long userMissionId,
            Long userId,
            Long missionId,
            UserMissionStatus status
    ){

    }
}
