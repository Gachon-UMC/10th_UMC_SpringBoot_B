package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.MemberMission;
import org.springframework.data.domain.Page;

import java.util.List;

public class MissionConverter {

    public static MissionResDTO.MissionListResponse toMissionListResponse(Page<MemberMission> page) {
        List<MissionResDTO.MissionDTO> missions = page.getContent().stream()
                .map(MissionConverter::toMissionDTO)
                .toList();

        return MissionResDTO.MissionListResponse.builder()
                .missions(missions)
                .currentPage(page.getNumber())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .build();
    }

    public static MissionResDTO.MissionDTO toMissionDTO(MemberMission memberMission) {
        return MissionResDTO.MissionDTO.builder()
                .memberMissionId(memberMission.getId())
                .missionId(memberMission.getMission().getId())
                .storeName(memberMission.getMission().getStore().getStoreName())
                .condition(memberMission.getMission().getContent())
                .rewardPoint(memberMission.getMission().getReward().intValue())
                .status(memberMission.getStatus().name())
                .build();
    }

    public static MissionResDTO.MissionCompleteResponse toMissionCompleteResponse(MemberMission memberMission) {
        return MissionResDTO.MissionCompleteResponse.builder()
                .memberMissionId(memberMission.getId())
                .missionId(memberMission.getMission().getId())
                .status(memberMission.getStatus().name())
                .completedAt(memberMission.getCompletedAt())
                .build();
    }
}
