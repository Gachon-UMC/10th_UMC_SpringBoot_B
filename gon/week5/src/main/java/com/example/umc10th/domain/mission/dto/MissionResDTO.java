package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.enums.UserMissionStatus;
import com.example.umc10th.domain.user.enums.FoodName;
import lombok.Builder;

import java.util.List;

public class MissionResDTO {

    //offset피네이제이션 틀
    @Builder
    public record OffsetPagination<T>(
            List<T> data,
            int page,
            int size
            /* workbook에서는 사용자가 값을 보냈는지 안보냈는지 확인하기위해
            Integer를 쓴것 같은데 null을 주면 안될 것 같아서 int를 사용했다.
            어떤게 맞을지 피드백 해줌 좋겠어.
             */
    ){}

    //cursor피네이제이션 틀
    @Builder
    public record CursorPagination<T>(
            List<T> data,
            Boolean hasNext,
            String nextCusor,
            int size
    ){}

    @Builder
    public record GetMission(
            Long missionId,
            Long userMissionId,
            String storeName,
            String title,
            String description,
            int point,
            Long storeId,
            UserMissionStatus status
    ){

    }

    //홈 미션 화면
    @Builder
    public record GetHomeMission(
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
