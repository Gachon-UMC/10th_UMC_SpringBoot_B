package org.example.umc10thyongjae.domain.mission.repository;

import org.example.umc10thyongjae.domain.mission.entity.UserMission;
import org.example.umc10thyongjae.domain.mission.enums.MissionStatus;
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
                ORDER BY UM.user_mission_id DESC
                LIMIT :size OFFSET :offset
            """, nativeQuery = true)
    List<UserMission> findUserMissionByUserId(
            @Param("userId") Long userId,
            @Param("status") MissionStatus status,
            @Param("size") int size,
            @Param("offset") int offset
    );
}
