package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface UserMissionRepository extends JpaRepository<UserMission,Long> {
    @Query(
            value = """
                    select um
                    from UserMission um
                    join fetch um.mission m
                    join fetch m.store s
                    where um.user.id = :userId
                    order by um.id desc
                    """,
            countQuery = """
                    select count(um)
                    from UserMission um
                    where um.user.id = :userId
                    """
    )
    Page<UserMission> findMissionsByUserId(
            @Param("userId") Long userId,
            Pageable pageable
    );
}
