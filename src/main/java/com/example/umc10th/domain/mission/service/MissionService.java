package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.MissionListResDTO;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;

    public MissionListResDTO getMyMissions(Long userId, int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size);

        Page<MemberMission> result = memberMissionRepository.findByMemberId(userId, pageRequest);

        var missions = result.getContent().stream()
                .map(mm -> new MissionListResDTO.MissionDTO(
                        mm.getMission().getId(),
                        mm.getMission().getCondition(),
                        mm.getIsComplete() ? "완료" : "진행중"
                ))
                .collect(Collectors.toList());

        return new MissionListResDTO(
                missions,
                page,
                result.getSize()
        );
    }
    // 홈 화면에서 도전 가능한 미션 조회
    private final MissionRepository missionRepository;
    public MissionListResDTO getHomeMissions(Long locationId, int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size);

        Page<Mission> result =
                missionRepository.findAvailableMissions(locationId, pageRequest);

        var missions = result.getContent().stream()
                .map(m -> new MissionListResDTO.MissionDTO(
                        m.getId(),
                        m.getCondition(),
                        "도전가능"
                ))
                .toList();

        return new MissionListResDTO(
                missions,
                page,
                result.getSize()
        );
    }
}