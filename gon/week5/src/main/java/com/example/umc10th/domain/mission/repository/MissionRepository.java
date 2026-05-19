package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface MissionRepository  extends JpaRepository<Mission,Long> {

    @Query(
            value = """
                    select m
                    from Mission m
                    join fetch m.store s
                    where s.region.id = :regionId
                    order by m.id desc
                    """,
            countQuery = """
                    select count(m)
                    from Mission m
                    join m.store s
                    where s.region.id = :regionId
                    """
    )
    Page<Mission> findAvailableMissionsByRegionId(
            @Param("regionId") Long regionId,
            Pageable pageable
    );

    @Query(
                    """
                    select m
                    from Mission m
                    join fetch m.store s
                    where s.id = :storeId
                        and (:idCursor is null or m.id< :idCursor)
                    order by m.id desc
                    """)
    Slice<Mission> findMissionsByStoreIdAndLessThanOrderByIdDesc(
            @Param("storeId") Long storeId,
            @Param("idCursor") Long idCursor,
            Pageable pageable
    );

    @Query(
            """
            select m
            from Mission m
            join fetch m.store s
            where s.id = :storeId
            order by m.id desc
            """)
    Slice<Mission> findMissionByStoreIdOrderByIdDesc(
            @Param("storeId") Long storeId,
            Pageable pageable
    );
}
