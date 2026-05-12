package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

// 가게 Entity의 DB 접근을 담당하는 Repository입니다.
public interface StoreRepository extends JpaRepository<Store, Long> {
}
