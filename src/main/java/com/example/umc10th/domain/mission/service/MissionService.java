package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionChallengeResDTO;
import com.example.umc10th.domain.mission.dto.MissionCompleteResDTO;
import com.example.umc10th.domain.mission.dto.MissionListResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

// 미션 도메인의 비즈니스 로직을 처리하는 서비스입니다.
@Service
@RequiredArgsConstructor
public class MissionService {

    // 회원 Entity의 DB 접근을 담당하는 Repository입니다.
    private final MemberRepository memberRepository;

    // 회원 미션 매핑 Entity의 DB 접근을 담당하는 Repository입니다.
    private final MemberMissionRepository memberMissionRepository;

    // 미션 Entity의 DB 접근을 담당하는 Repository입니다.
    private final MissionRepository missionRepository;

    // 사용자의 미션 목록을 상태별로 조회합니다.
    public MissionListResDTO getMyMissions(Long userId, String status, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<MemberMission> result = memberMissionRepository.findByMemberIdAndStatus(
                userId,
                normalizeMissionStatus(status),
                pageRequest
        );

        return MissionConverter.toMyMissionListResDTO(result, page);
    }

    // 사용자가 특정 미션에 도전합니다.
    @Transactional
    public MissionChallengeResDTO challengeMission(Long userId, Long missionId) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        if (memberMissionRepository.existsByMemberIdAndMissionId(userId, missionId)) {
            throw new MissionException(MissionErrorCode.MISSION_ALREADY_CHALLENGED);
        }

        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .isComplete(false)
                .build();

        MemberMission savedMemberMission = memberMissionRepository.save(memberMission);

        return MissionConverter.toMissionChallengeResDTO(savedMemberMission);
    }

    // 사용자의 미션 성공 인증을 처리합니다.
    @Transactional
    public MissionCompleteResDTO completeMission(Long userId, Long userMissionId) {
        MemberMission memberMission = memberMissionRepository.findById(userMissionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MEMBER_MISSION_NOT_FOUND));

        if (!memberMission.getMember().getId().equals(userId)) {
            throw new MissionException(MissionErrorCode.MISSION_NOT_BELONG_TO_USER);
        }

        memberMission.complete();

        return MissionConverter.toMissionCompleteResDTO(memberMission);
    }

    // 홈 화면에서 도전 가능한 미션 목록을 조회합니다.
    public MissionListResDTO getHomeMissions(Long locationId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Mission> result = missionRepository.findAvailableMissions(locationId, pageRequest);

        return MissionConverter.toHomeMissionListResDTO(result, page);
    }

    // 요청 상태값을 Repository 조회 조건으로 변환합니다.
    private Boolean normalizeMissionStatus(String status) {
        if (status == null || "all".equalsIgnoreCase(status)) {
            return null;
        }

        if ("in-progress".equalsIgnoreCase(status) || "IN_PROGRESS".equalsIgnoreCase(status)) {
            return false;
        }

        if ("completed".equalsIgnoreCase(status) || "complete".equalsIgnoreCase(status)) {
            return true;
        }

        throw new MissionException(MissionErrorCode.MISSION_STATUS_NOT_VALID);
    }
}
