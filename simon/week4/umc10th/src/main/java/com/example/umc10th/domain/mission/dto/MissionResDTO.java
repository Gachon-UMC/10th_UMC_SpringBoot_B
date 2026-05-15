package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.member_mission.enums.MissionStatus;
import com.example.umc10th.domain.store.enums.RegionName;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {
    // 모든 목록 조회 API에서 사용하는 공통 페이지네이션 응답 규격
    // 페이지네이션 틀
    @Builder
    public record Pagination<T>(
            List<T> data,
            Integer pageNumber,
            Integer pageSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    // 가게 상세, 특정 가게에 등록된 단일 미션 정보
    @Builder
    public record GetMission(
            Long missionId,
            Integer point,
            String mission_condition
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

    // 미션 도전, 사용자가 미션 도전하기를 눌렀을 때의 응답 데이터
    @Builder
    public record ChallengeMission(
            Long missionId,
            String message
    ) {}

    // 미션 완료, 사장님 인증 후 미션이 최종 완료(DONE) 되었을 때의 응답
    @Builder
    public record CompleteMission(
            Long memberMissionId,
            MissionStatus missionStatus,
            String verificationCode
    ) {}

    // 사용자가 성공 요청 시 사장님께 보여드릴 8자리 번호 발급 결과
    @Builder
    public record VerifyMission(
            Long proofId,
            String message,     // 발급된 8자리 랜덤 번호
            String createdAt
    ) {}

    // 마이페이지, 내가 도전 중이거나 완료한 미션의 단일 항목 정보
    @Builder
    public record MemberMissionItem(
            String storeName,
            Integer point,
            MissionStatus missionStatus,
            String missionCondition
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

    // 홈 화면 통합 응답 DTO
    // 상단 진행률 데이터와 하단 미션 리스트를 모두 포함
    @Builder
    public record HomeMissionResponse(
            Long completedMissionCount,
            Integer missionGoal,
            Pagination<HomeMissionItem> missionData
    ) {}
}
