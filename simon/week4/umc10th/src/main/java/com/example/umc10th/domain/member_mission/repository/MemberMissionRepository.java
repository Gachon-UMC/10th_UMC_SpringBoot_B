package com.example.umc10th.domain.member_mission.repository;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member_mission.entity.MemberMission;
import com.example.umc10th.domain.member_mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    // 사용자의 미션 도전 중복 여부 확인 (동일 미션 중복 수락 방지용)
    boolean existsByMemberAndMissionAndMissionStatus(Member member, Mission mission, MissionStatus missionStatus);

    // 나의 미션 목록 조회 (진행 중 / 완료)
    @Query("""
    select mm from MemberMission mm
    join fetch mm.mission m
    join fetch m.store
    where mm.member.id = :memberId and mm.missionStatus = :status
    """)
    Page<MemberMission> findAllByMemberIdAndMissionStatus(
            @Param("memberId") Long memberId,
            @Param("status") MissionStatus status,
            Pageable pageable
    );
    // 사용자의 특정 상태인 미션 개수를 세는 메서드
    long countByMemberIdAndMissionStatus(Long memberId, MissionStatus missionStatus);
}
