package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.enums.Status;
import com.example.umc10th.domain.store.enums.RegionName;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {
    // 목록 조회
    @Builder
    public record MissionList(
            List<MissionPreviewDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    @Builder
    public record MissionPreviewDTO(
            Long missionId,
            String storeName,
            Integer reward,
            LocalDate deadline,
            Status status
    ) {}

    // 미션 도전하기 결과
    @Builder
    public record ChallengeMission(
            Long missionId,
            String message
    ) {}

    // 미션 성공 누르기 결과
    @Builder
    public record CompleteMission(
            Long userMissionId,
            Status status
    ) {}

    // 미션 인증 결과
    @Builder
    public record VerifyMission(
            Long proofId,
            String message,
            String createdAt
    ) {}

    @Builder
    public record MemberMissionItem(
            String storeName,
            Integer reward,
            Status status,
            String missionCondition
    ) {}

    @Builder
    public record MemberMissionList(
            List<MemberMissionItem> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    @Builder
    public record HomeMissionItem(
            Long missionId,
            String storeName,
            RegionName regionName,
            String missionCondition,
            Integer reward,
            LocalDateTime deadline
    ) {}

    @Builder
    public record HomeMissionList(
            List<HomeMissionItem> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast,
            Long activeMissionCount
    ) {}
}
