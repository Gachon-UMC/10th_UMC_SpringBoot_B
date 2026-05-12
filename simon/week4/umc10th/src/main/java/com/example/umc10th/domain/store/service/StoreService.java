package com.example.umc10th.domain.store.service;

import com.example.umc10th.domain.store.converter.StoreConverter;
import com.example.umc10th.domain.store.dto.StoreResDTO;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.exception.StoreException;
import com.example.umc10th.domain.store.exception.code.StoreErrorCode;
import com.example.umc10th.domain.store.repository.StoreRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {
    private final StoreRepository storeRepository;

    // 지역별 가게 목록 페이징 조회
    public StoreResDTO.StoreSummaryList getStoreListByRegion(Long regionId, Integer page) {
        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        // findAllByRegionId 호출
        Page<Store> storePage = storeRepository.findAllByRegionId(regionId, pageRequest);

        return StoreConverter.toStoreSummaryList(storePage);
    }

    // 단일 가게 정보 조회
    public StoreResDTO.GetStoreInfo getStoreInfo(Long storeId) {
        Store store = storeRepository.findByIdWithAll(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        return StoreConverter.toGetStoreInfo(store);
    }
}
