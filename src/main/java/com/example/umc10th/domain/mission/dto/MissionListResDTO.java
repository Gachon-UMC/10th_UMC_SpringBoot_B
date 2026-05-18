package com.example.umc10th.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

// 응답 DTO 필드 조회를 위한 Getter를 생성합니다.
@Getter
// 응답 DTO 생성 시 builder 패턴을 사용할 수 있게 합니다.
@Builder
// 모든 필드를 받는 생성자를 생성합니다.
@AllArgsConstructor
public class MissionListResDTO {

    // 조회된 미션 목록입니다.
    private List<MissionDTO> missions;

    // 현재 페이지 또는 커서 값입니다.
    private int cursor;

    // 현재 응답에 포함된 데이터 개수입니다.
    private int size;

    // 미션 목록의 개별 항목 DTO입니다.
    @Getter
    // 개별 미션 DTO 생성 시 builder 패턴을 사용할 수 있게 합니다.
    @Builder
    // 모든 필드를 받는 생성자를 생성합니다.
    @AllArgsConstructor
    public static class MissionDTO {

        // 미션 ID입니다.
        private Long missionId;

        // 미션 제목 또는 조건 문구입니다.
        private String title;

        // 미션 진행 상태입니다.
        private String status;
    }
}
