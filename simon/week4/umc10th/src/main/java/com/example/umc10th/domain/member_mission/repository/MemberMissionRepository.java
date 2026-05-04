package com.example.umc10th.domain.member_mission.repository;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    Page<MemberMission> findAllByStatus(Status status, Pageable pageable);

    @Query("""
            select new com.example.umc10th.domain.mission.dto.MissionResDTO$MemberMissionItem(
                s.storeName,
                m.reward,
                mm.status,
                m.missionCondition
            )
            from MemberMission mm
            join mm.mission m
            join m.store s
            where mm.member.id = :memberId
              and mm.status = :status
            order by mm.updatedAt desc
            """)
    Page<MissionResDTO.MemberMissionItem> findMemberMissionsByMemberIdAndStatus(
            Long memberId,
            Status status,
            Pageable pageable
    );
}
