package com.example.umc10th.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

// 응답 DTO 필드 조회를 위한 Getter를 생성합니다.
@Getter
// 응답 DTO 생성 시 builder 패턴을 사용할 수 있게 합니다.
@Builder
// 모든 필드를 받는 생성자를 생성합니다.
@AllArgsConstructor
public class MissionChallengeResDTO {

    // 생성된 사용자 미션 ID입니다.
    private Long userMissionId;

    // 도전한 미션 ID입니다.
    private Long missionId;

    // 미션 진행 상태입니다.
    private String status;
}
