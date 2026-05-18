package com.example.umc10th.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

// 미션 도메인 응답 DTO를 관리합니다.
public class MissionResDTO {

    // 오프셋 기반 페이지네이션 응답 DTO입니다.
    @Getter
    @Builder
    @AllArgsConstructor
    public static class Pagination<T> {

        // 현재 페이지의 데이터 목록입니다.
        private List<T> data;

        // 현재 페이지 번호입니다.
        private Integer pageNumber;

        // 현재 페이지 크기입니다.
        private Integer pageSize;
    }

    // 미션 조회 응답 DTO입니다.
    @Getter
    @Builder
    @AllArgsConstructor
    public static class GetMission {

        // 미션 ID입니다.
        private Long missionId;

        // 미션 보상 포인트입니다.
        private Integer point;

        // 미션 달성 조건입니다.
        private String conditional;
    }
}
