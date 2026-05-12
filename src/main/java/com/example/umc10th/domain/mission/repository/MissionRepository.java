package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// 미션 Entity의 DB 접근을 담당하는 Repository입니다.
public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 지역 ID 기준으로 도전 가능한 미션을 조회하는 JPQL입니다.
    @Query("""
            SELECT m
            FROM Mission m
            JOIN m.store s
            WHERE s.location.id = :locationId
            """)
    Page<Mission> findAvailableMissions(@Param("locationId") Long locationId, Pageable pageable);
}
