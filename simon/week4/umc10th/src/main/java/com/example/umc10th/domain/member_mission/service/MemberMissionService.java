package com.example.umc10th.domain.member_mission.service;

import com.example.umc10th.domain.member_mission.dto.MemberMissionReqDTO;
import com.example.umc10th.domain.member_mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.member_mission.entity.MemberMission;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberMissionService {
    private final MemberMissionRepository memberMissionRepository;

    // 나의 미션 목록 조회 (진행 중 / 완료)
    @Transactional(readOnly = true)
    public Page<MemberMission> getMemberMissionList(MemberMissionReqDTO.MyMissionRequest dto) {
        // 1. 페이지 번호와 크기 설정 (기본값 처리)
        // 사용자가 1페이지를 요청하면 서버는 0페이지를 가져와야 함
        int page = (dto.page() == null || dto.page() < 1) ? 0 : dto.page() - 1;
        int size = (dto.size() == null || dto.size() == 0) ? 10 : dto.size();

        // 2. Repository 호출
        return memberMissionRepository.findAllByMemberIdAndMissionStatus(
                dto.memberId(),
                dto.missionStatus(),
                PageRequest.of(page, size)
        );
    }
}
