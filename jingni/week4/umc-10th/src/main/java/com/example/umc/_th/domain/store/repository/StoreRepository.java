package com.example.umc._th.domain.store.repository;

import com.example.umc._th.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store,Long> {
}
