package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionChallengeResDTO;
import com.example.umc10th.domain.mission.dto.MissionCompleteResDTO;
import com.example.umc10th.domain.mission.dto.MissionListResDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;

import java.util.List;

// 미션 도메인의 Entity와 DTO 변환을 담당합니다.
public class MissionConverter {

    // 회원 미션 Entity를 미션 도전 응답 DTO로 변환합니다.
    public static MissionChallengeResDTO toMissionChallengeResDTO(MemberMission memberMission) {
        return MissionChallengeResDTO.builder()
                .userMissionId(memberMission.getId())
                .missionId(memberMission.getMission().getId())
                .status("IN_PROGRESS")
                .build();
    }

    // 회원 미션 Entity를 미션 완료 응답 DTO로 변환합니다.
    public static MissionCompleteResDTO toMissionCompleteResDTO(MemberMission memberMission) {
        return MissionCompleteResDTO.builder()
                .userMissionId(memberMission.getId())
                .status("COMPLETED")
                .build();
    }

    // 회원 미션 Entity 목록을 내 미션 목록 응답 DTO로 변환합니다.
    public static MissionListResDTO toMyMissionListResDTO(Page<MemberMission> memberMissions, int page) {
        List<MissionListResDTO.MissionDTO> missions = memberMissions.getContent().stream()
                .map(MissionConverter::toMyMissionDTO)
                .toList();

        return MissionListResDTO.builder()
                .missions(missions)
                .cursor(page)
                .size(memberMissions.getSize())
                .build();
    }

    // 미션 Entity 목록을 홈 화면 미션 목록 응답 DTO로 변환합니다.
    public static MissionListResDTO toHomeMissionListResDTO(Page<Mission> missionPage, int page) {
        List<MissionListResDTO.MissionDTO> missions = missionPage.getContent().stream()
                .map(MissionConverter::toHomeMissionDTO)
                .toList();

        return MissionListResDTO.builder()
                .missions(missions)
                .cursor(page)
                .size(missionPage.getSize())
                .build();
    }

    // 진행중인 회원 미션 목록을 페이지네이션 응답 DTO로 변환합니다.
    public static MissionResDTO.Pagination<MissionResDTO.GetMission> toInProgressPagination(
            Page<MemberMission> memberMissions
    ) {
        List<MissionResDTO.GetMission> missions = memberMissions.getContent().stream()
                .map(MissionConverter::toGetMission)
                .toList();

        return MissionResDTO.Pagination.<MissionResDTO.GetMission>builder()
                .data(missions)
                .pageNumber(memberMissions.getNumber())
                .pageSize(memberMissions.getSize())
                .build();
    }

    // 회원 미션 Entity를 내 미션 목록의 개별 DTO로 변환합니다.
    private static MissionListResDTO.MissionDTO toMyMissionDTO(MemberMission memberMission) {
        return MissionListResDTO.MissionDTO.builder()
                .missionId(memberMission.getMission().getId())
                .title(memberMission.getMission().getCondition())
                .status(memberMission.getIsComplete() ? "COMPLETED" : "IN_PROGRESS")
                .build();
    }

    // 미션 Entity를 홈 화면 미션 목록의 개별 DTO로 변환합니다.
    private static MissionListResDTO.MissionDTO toHomeMissionDTO(Mission mission) {
        return MissionListResDTO.MissionDTO.builder()
                .missionId(mission.getId())
                .title(mission.getCondition())
                .status("AVAILABLE")
                .build();
    }

    // 회원 미션 Entity를 미션 조회 응답 DTO로 변환합니다.
    private static MissionResDTO.GetMission toGetMission(MemberMission memberMission) {
        Mission mission = memberMission.getMission();

        return MissionResDTO.GetMission.builder()
                .missionId(mission.getId())
                .point(mission.getPoint())
                .conditional(mission.getCondition())
                .build();
    }
}
