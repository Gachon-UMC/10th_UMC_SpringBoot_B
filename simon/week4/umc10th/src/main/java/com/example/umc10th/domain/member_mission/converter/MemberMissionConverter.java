package com.example.umc10th.domain.member_mission.converter;

import com.example.umc10th.domain.member_mission.entity.MemberMission;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.global.apiPayload.dto.PageResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public class MemberMissionConverter {
    // 공통 Pagination 변환
    public static PageResponse<MissionResDTO.MemberMissionItem> toMemberMissionPagination(Page<MemberMission> memberMissionPage) {
        List<MissionResDTO.MemberMissionItem> data = memberMissionPage.getContent().stream()
                .map(mm -> MissionResDTO.MemberMissionItem.builder()
                        .storeName(mm.getMission().getStore().getStoreName())
                        .point(mm.getMission().getPoint())
                        .missionStatus(mm.getMissionStatus())
                        .missionCondition(mm.getMission().getMissionCondition())
                        .build())
                .toList();

        return PageResponse.of(memberMissionPage, data);
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