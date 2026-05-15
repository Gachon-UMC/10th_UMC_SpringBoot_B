package com.example.umc10th.domain.member_mission.service;

import com.example.umc10th.domain.member_mission.entity.MemberMission;
import com.example.umc10th.domain.member_mission.enums.MissionStatus;
import com.example.umc10th.domain.member_mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberMissionService {
    private final MemberMissionRepository memberMissionRepository;

    /**
     * 나의 미션 목록 조회 (진행 중 / 완료)
     */
    @Transactional(readOnly = true)
    public Page<MemberMission> getMemberMissionList(Long memberId, MissionStatus status, Pageable pageable) {
         Pageable adjustedPageable = PageRequest.of(
                Math.max(0, pageable.getPageNumber() - 1),
                pageable.getPageSize(),
                pageable.getSort()
        );

        return memberMissionRepository.findAllByMemberIdAndMissionStatus(memberId, status, adjustedPageable);
    }
}
