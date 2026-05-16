package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.member_mission.enums.MissionStatus;
import com.example.umc10th.domain.store.enums.RegionName;
import com.example.umc10th.global.apiPayload.dto.PageResponse;
import lombok.Builder;

import java.time.LocalDateTime;

public class MissionResDTO {

    // 가게 상세, 특정 가게에 등록된 단일 미션 정보
    @Builder
    public record GetMission(
            Long missionId,
            Integer point,
            String missionCondition
    ) {}

    // 미션 도전 시작 시 응답
    @Builder
    public record ChallengeMission(
            Long missionId,
            String message
    ) {}

    // 미션 성공 버튼 클릭 시
    @Builder
    public record CompleteMission(
            Long memberMissionId,
            MissionStatus missionStatus,
            String verificationCode
    ) {}

    // 사장님 확인 후 최종 승인 시 응답
    @Builder
    public record VerifyMission(
            Long proofId,
            String message,
            String createdAt
    ) {}

    // 홈 화면 통합 응답 DTO (상단 요약 + 하단 리스트)
    @Builder
    public record HomeMissionResponse(
            Long completedMissionCount,
            Integer missionGoal,
            PageResponse<HomeMissionItem> missionData
    ) {}

    // 메인 홈, 홈 화면의 우리 동네 미션 리스트에 표시될 단일 항목
    @Builder
    public record HomeMissionItem(
            Long missionId,
            String storeName,
            RegionName regionName,
            String missionCondition,
            Integer point,
            LocalDateTime deadline
    ) {}

    // 마이페이지, 내가 도전 중이거나 완료한 미션의 단일 항목 정보
    @Builder
    public record MemberMissionItem(
            String storeName,
            Integer point,
            MissionStatus missionStatus,
            String missionCondition
    ) {}

    // 목록 화면에 표시될 미션의 요약 정보
    @Builder
    public record MissionPreviewDTO(
            Long missionId,
            String storeName,
            Integer point,
            LocalDateTime deadline,
            MissionStatus missionStatus
    ) {}
}
