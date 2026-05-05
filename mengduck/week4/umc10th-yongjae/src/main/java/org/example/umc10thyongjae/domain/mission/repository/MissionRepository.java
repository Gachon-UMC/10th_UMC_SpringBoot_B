package org.example.umc10thyongjae.domain.mission.repository;

import org.example.umc10thyongjae.domain.mission.entity.Mission;
import org.example.umc10thyongjae.domain.mission.entity.UserMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query(value = """
                SELECT * 
                FROM mission M
                WHERE M.location = :location
                  AND M.deleted_yn = 'N'
                ORDER BY M.created_at DESC
                LIMIT :size OFFSET :offset
            """, nativeQuery = true)
    List<Mission> findMissionByLocation(
            @Param("location") String location,
            @Param("size") int size,
            @Param("offset") int offset
    );
}
