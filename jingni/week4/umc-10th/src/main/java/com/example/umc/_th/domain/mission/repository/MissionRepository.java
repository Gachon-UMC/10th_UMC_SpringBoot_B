package com.example.umc._th.domain.mission.repository;

import com.example.umc._th.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 마감 순
    @Query(value = """
        SELECT m.*
        FROM mission m
        JOIN store s ON m.store_id = s.id
        WHERE s.region_id = :regionId
          AND m.is_active = 1
        ORDER BY m.deadline DESC
        LIMIT :limit OFFSET :offset
    """, nativeQuery = true)

    List<Mission> findByRegion(
            @Param("regionId") Long regionId,
            @Param("limit") int limit,
            @Param("offset") int offset
    );

    // 포인트 순
    @Query(value = """
        SELECT m.*
        FROM mission m
        JOIN store s ON m.store_id = s.id
        WHERE s.region_id = :regionId
          AND m.is_active = 1
        ORDER BY m.point DESC
        LIMIT :limit OFFSET :offset
    """, nativeQuery = true)

    List<Mission> findByRegionOrderByPoint(
            @Param("regionId") Long regionId,
            @Param("limit") int limit,
            @Param("offset") int offset
    );

    @Query(value = """
        SELECT m.*
        FROM mission m
        JOIN member_mission mm ON mm.mission_id = m.id
        WHERE mm.member_id = :memberId
          AND (:status IS NULL OR mm.status = :status)
        ORDER BY m.id DESC
        LIMIT :limit OFFSET :offset
    """, nativeQuery = true)

    List<Mission> findMyMissions(
            @Param("memberId") Long memberId,
            @Param("status") String status,
            @Param("limit") int limit,
            @Param("offset") int offset
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