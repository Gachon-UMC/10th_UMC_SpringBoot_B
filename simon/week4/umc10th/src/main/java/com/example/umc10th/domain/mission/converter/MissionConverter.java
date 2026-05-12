package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.store.entity.Store;
import org.springframework.data.domain.Page;

import java.util.List;

public class MissionConverter {
    // 미션 생성, 사용자가 입력한 DTO 데이터를 Mission 엔티티로 변환
    public static Mission toMission(
            Store store,
            MissionReqDTO.CreateMission dto
    ) {
        return Mission.builder()
                .store(store)
                .missionCondition(dto.mission_condition())
                .point(dto.point())
                .deadline(dto.deadline())
                .build();
    }

    // 다양한 목록 데이터를 공통 페이지네이션 응답 형식으로 포장
    // 페이지네이션 틀 생성
    public static <T> MissionResDTO.Pagination<T> toPagination(
            Page<?> page,  // 리스트, 페이지 번호 등이 다 들어있는 통짜 객체
            List<T> data   // 이미 변환이 완료된 DTO 리스트
    ) {
        return MissionResDTO.Pagination.<T>builder()
                .data(data)
                .pageNumber(page.getNumber() + 1)
                .pageSize(page.getSize())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }

    // 가게 내 미션 조회, 미션 엔티티를 특정 가게 상세 페이지용 응답 DTO로 변환
    public static MissionResDTO.GetMission toGetMission(
            Mission mission
    ) {
        return MissionResDTO.GetMission.builder()
                .mission_condition(mission.getMissionCondition())
                .point(mission.getPoint())
                .missionId(mission.getId())
                .build();
    }
}
