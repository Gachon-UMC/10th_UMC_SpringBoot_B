package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.store.enums.RegionName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    // 현재 선택된 지역에서 도전 가능한 미션 목록 조회
    @Query("""
            select new com.example.umc10th.domain.mission.dto.MissionResDTO$HomeMissionItem(
                m.id,
                s.storeName,
                r.regionName,
                m.missionCondition,
                m.point,
                m.deadline
            )
            from Mission m
            join m.store s
            join s.region r
            left join MemberMission mm
                on mm.mission = m and mm.member.id = :memberId
            where r.regionName = :regionName
                and mm.id is null
                and m.deadline > current_timestamp()
            """)
    Page<MissionResDTO.HomeMissionItem> findHomeMissionsByRegionAndMember(
            @Param("memberId") Long memberId,
            @Param("regionName") RegionName regionName,
            Pageable pageable
    );

    // 특정 가게의 미션 목록 조회
    Page<Mission> findAllByStoreId(Long storeId, Pageable pageable);
}
