package com.example.umc._th.domain.store.repository;

import com.example.umc._th.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {
    @Query("""
    SELECT s.id
    FROM Store s
    WHERE s.region.id = :regionId
""")
    List<Long> findStoreIdsByRegionId( @Param("regionId") Long regionId );
}
