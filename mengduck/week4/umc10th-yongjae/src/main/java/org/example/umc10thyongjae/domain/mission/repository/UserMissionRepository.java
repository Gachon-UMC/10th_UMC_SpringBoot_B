package org.example.umc10thyongjae.domain.mission.repository;

import org.example.umc10thyongjae.domain.mission.entity.UserMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    @Query(value = """
        SELECT *
        FROM user_mission UM
        WHERE UM.user_id = :userId
          AND (:status IS NULL OR UM.status = :status)
        ORDER BY UM.created_at DESC
        """, nativeQuery = true)
    Page<UserMission> findUserMissionByUserId(
            @Param("userId") Long userId,
            @Param("status") String status,
            Pageable pageable
    );
}
