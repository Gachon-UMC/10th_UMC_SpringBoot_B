package com.example.umc._th.domain.mission.repository;

import com.example.umc._th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query(value = """
    SELECT m.*
    FROM mission m
    JOIN store s ON m.store_id = s.id
    WHERE s.region_id = :regionId
      AND m.is_active = 1
    """,
            countQuery = """
    SELECT COUNT(*)
    FROM mission m
    JOIN store s ON m.store_id = s.id
    WHERE s.region_id = :regionId
      AND m.is_active = 1
    """,
            nativeQuery = true
    )
    Page<Mission> findByRegion(
            @Param("regionId") Long regionId,
            Pageable pageable
    );

    @Query(value = """
    SELECT m.*
    FROM mission m
    JOIN member_mission mm ON mm.mission_id = m.id
    WHERE mm.member_id = :memberId
      AND (:status IS NULL OR mm.status = :status)
    """,
            countQuery = """
    SELECT COUNT(*)
    FROM mission m
    JOIN member_mission mm ON mm.mission_id = m.id
    WHERE mm.member_id = :memberId
      AND (:status IS NULL OR mm.status = :status)
    """,
            nativeQuery = true
    )
    Page<Mission> findMyMissions(
            @Param("memberId") Long memberId,
            @Param("status") String status,
            Pageable pageable
    );

    @Query(value = """
    SELECT COUNT(*)
    FROM member_mission mm
    JOIN mission m ON mm.mission_id = m.id
    JOIN store s ON m.store_id = s.id
    WHERE mm.member_id = :memberId
      AND mm.status = 'COMPLETED'
      AND s.region_id = :regionId
""", nativeQuery = true)
    Integer countCompletedMissionsByRegion(
            @Param("memberId") Long memberId,
            @Param("regionId") Long regionId
    );
}