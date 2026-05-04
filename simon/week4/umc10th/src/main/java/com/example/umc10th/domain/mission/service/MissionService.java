package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.member_mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.Status;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.store.enums.RegionName;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    public MissionResDTO.MissionList missionList(Long memberId, Status status, int page, int size) {
        // 1. 페이징 객체 생성
        PageRequest pageRequest = PageRequest.of(page, size);

        // 2. DB에서 상태에 따른 미션 조회 (MemberMission 기준)
        Page<MemberMission> memberMissions =
                memberMissionRepository.findAllByMemberIdAndStatus(memberId, status, pageRequest);

        // 3. DTO로 변환하여 반환 (Converter 활용)
        return MissionConverter.toMissionList(memberMissions);
    }

    @Transactional
    public MissionResDTO.ChallengeMission challengeMission(Long missionId, MissionReqDTO.ChallengeMission dto) {
        return MissionResDTO.ChallengeMission.builder()
                .missionId(missionId)
                .message("임시: 미션 도전 시작 로직 구현 예정")
                .build();
    }

    public MissionResDTO.CompleteMission completeMission(Long userMissionId) {
        return MissionResDTO.CompleteMission.builder()
                .userMissionId(userMissionId)
                .status(Status.ONGOING) // 임시 상태
                .build();
    }

    @Transactional
    public MissionResDTO.VerifyMission verifyMission(Long missionId, MissionReqDTO.VerifyMission dto) {
        return MissionResDTO.VerifyMission.builder()
                .message("미션 성공 인증 성공")
                .build();
    }

    public MissionResDTO.MemberMissionList memberMissionList(Long memberId, Status status, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        Page<MissionResDTO.MemberMissionItem> memberMissions =
                memberMissionRepository.findMemberMissionsByMemberIdAndStatus(memberId, status, pageRequest);

        return MissionResDTO.MemberMissionList.builder()
                .missionList(memberMissions.getContent())
                .listSize(memberMissions.getNumberOfElements())
                .totalPage(memberMissions.getTotalPages())
                .totalElements(memberMissions.getTotalElements())
                .isFirst(memberMissions.isFirst())
                .isLast(memberMissions.isLast())
                .build();
    }

    public MissionResDTO.HomeMissionList homeMissionList(
            Long memberId,
            RegionName regionName,
            Status status,
            int page,
            int size
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);

        Page<MissionResDTO.HomeMissionItem> missions =
                missionRepository.findHomeMissionsByRegionAndMember(memberId, regionName, pageRequest);

        long activeMissionCount = memberMissionRepository.countByMemberIdAndStatus(memberId, status);

        return MissionResDTO.HomeMissionList.builder()
                .missionList(missions.getContent())
                .listSize(missions.getNumberOfElements())
                .totalPage(missions.getTotalPages())
                .totalElements(missions.getTotalElements())
                .isFirst(missions.isFirst())
                .isLast(missions.isLast())
                .activeMissionCount(activeMissionCount)
                .build();
    }
}
