package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.store.enums.RegionName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    @Query("""
            select new com.example.umc10th.domain.mission.dto.MissionResDTO$HomeMissionItem(
                m.id,
                s.storeName,
                r.regionName,
                m.missionCondition,
                m.reward,
                m.deadline
            )
            from Mission m
            join m.store s
            join s.region r
            left join com.example.umc10th.domain.mission.entity.mapping.MemberMission mm
                on mm.mission = m and mm.member.id = :memberId
            where r.regionName = :regionName
              and mm.id is null
              and m.deadline > current_timestamp
            order by m.createdAt desc
            """)
    Page<MissionResDTO.HomeMissionItem> findHomeMissionsByRegionAndMember(
            Long memberId,
            RegionName regionName,
            Pageable pageable
    );
}
