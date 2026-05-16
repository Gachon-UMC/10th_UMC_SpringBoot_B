package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.store.enums.RegionName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    // 특정 가게의 모든 미션 목록을 페이징하여 조회
    Page<Mission> findAllByStoreId(Long storeId, Pageable pageable);

    // 현재 선택된 지역에서 도전 가능한 미션 목록 조회
    @Query("""
            select m
            from Mission m
            join fetch m.store s
            join fetch s.region r
            left join MemberMission mm
                on mm.mission = m and mm.member.id = :memberId
            where r.regionName = :regionName
                and mm.id is null
                and m.deadline > current_timestamp
            """)
    Page<Mission> findHomeMissionsByRegionAndMember(
            @Param("memberId") Long memberId,
            @Param("regionName") RegionName regionName,
            Pageable pageable
    );
}
