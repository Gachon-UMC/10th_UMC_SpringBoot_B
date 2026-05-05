package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.MemberMission;
import com.example.umc10th.domain.mission.entity.MissionStatus;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;

    @Transactional(readOnly = true)
    public MissionResDTO.MissionListResponse getMissions(Long userId, String status, int page, int size) {
        MissionStatus missionStatus = status.equals("complete")
                ? MissionStatus.SUCCESS
                : MissionStatus.PROGRESS;

        Page<MemberMission> memberMissionPage = memberMissionRepository
                .findByMemberIdAndStatus(userId, missionStatus, PageRequest.of(page, size));

        return MissionConverter.toMissionListResponse(memberMissionPage);
    }

    @Transactional
    public MissionResDTO.MissionCompleteResponse completeMission(Long userId, Long missionId) {
        MemberMission memberMission = memberMissionRepository.findById(missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MEMBER_MISSION_NOT_FOUND));

        if (memberMission.getStatus() == MissionStatus.SUCCESS) {
            throw new MissionException(MissionErrorCode.MISSION_ALREADY_COMPLETED);
        }

        memberMission.complete();

        return MissionConverter.toMissionCompleteResponse(memberMission);
    }
}
