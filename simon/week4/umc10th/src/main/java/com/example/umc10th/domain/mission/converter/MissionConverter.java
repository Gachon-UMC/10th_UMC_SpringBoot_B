package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.global.apiPayload.dto.PageResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public class MissionConverter {
    // 미션 생성 (DTO -> Entity)
    public static Mission toMission(Store store, MissionReqDTO.CreateMission dto) {
        return Mission.builder()
                .store(store)
                .missionCondition(dto.missionCondition())
                .point(dto.point())
                .deadline(dto.deadline())
                .build();
    }

    public static MissionResDTO.HomeMissionItem toHomeMissionItem(Mission mission) {
        return MissionResDTO.HomeMissionItem.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getStoreName())
                .regionName(mission.getStore().getRegion().getRegionName())
                .missionCondition(mission.getMissionCondition())
                .point(mission.getPoint())
                .deadline(mission.getDeadline())
                .build();
    }

    // 단일 미션 조회 DTO 변환 (Entity -> DTO)
    public static MissionResDTO.GetMission toGetMission(Mission mission) {
        return MissionResDTO.GetMission.builder()
                .missionCondition(mission.getMissionCondition())
                .point(mission.getPoint())
                .missionId(mission.getId())
                .build();
    }

    // 가게 내 미션 목록 페이지 변환 (Page<Entity> -> PageResponse<DTO>)
    public static PageResponse<MissionResDTO.GetMission> toGetMissionPage(Page<Mission> missionPage) {
        List<MissionResDTO.GetMission> data = missionPage.map(MissionConverter::toGetMission).toList();

        return PageResponse.of(missionPage, data);
    }

    // 공통 페이지네이션 응답 규격 (제네릭)
    public static <T, E> PageResponse<T> toPageResponse(Page<E> page, List<T> data) {
        return PageResponse.of(page, data);
    }
}
