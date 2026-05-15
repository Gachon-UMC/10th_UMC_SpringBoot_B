package com.example.umc10th.domain.store.repository;

import com.example.umc10th.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    // 특정 지역(Region) ID로 해당 지역의 가게 목록을 페이징 조회
    Page<Store> findAllByRegionId(Long regionId, Pageable pageable);

    // 단일 가게 상세 정보 조회
    @Query("SELECT s FROM Store s JOIN FETCH s.region JOIN FETCH s.foodCategory WHERE s.id = :storeId")
    Optional<Store> findByIdWithAll(@Param("storeId") Long storeId);
}
