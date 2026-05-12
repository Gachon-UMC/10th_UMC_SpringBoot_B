package com.example.umc10th.domain.member_mission.converter;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.member_mission.entity.MemberMission;
import org.springframework.data.domain.Page;

import java.util.List;

public class MemberMissionConverter {
    // 공통 Pagination 변환 (MissionConverter 활용)
    public static MissionResDTO.Pagination<MissionResDTO.MemberMissionItem> toMemberMissionPagination(
            Page<MemberMission> memberMissions
    ) {
        List<MissionResDTO.MemberMissionItem> data = memberMissions.stream()
                .map(MemberMissionConverter::toMemberMissionItem)
                .toList();

        return MissionConverter.toPagination(memberMissions, data);
    }

    // 엔티티 -> 아이템 DTO 변환 (단일 항목)
    public static MissionResDTO.MemberMissionItem toMemberMissionItem(MemberMission memberMission) {
        return MissionResDTO.MemberMissionItem.builder()
                .storeName(memberMission.getMission().getStore().getStoreName())
                .point(memberMission.getMission().getPoint())
                .missionStatus(memberMission.getMissionStatus())
                .missionCondition(memberMission.getMission().getMissionCondition())
                .build();
    }
}