package com.example.umc10th.domain.store.service;

import com.example.umc10th.domain.store.dto.StoreResDTO;
import com.example.umc10th.domain.store.enums.RegionName;
import com.example.umc10th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    public StoreResDTO.GetStoreDetail getStoreDetail(Long storeId) {
        return StoreResDTO.GetStoreDetail.builder()
                .storeId(storeId)
                .storeName("메가커피")
                .regionId(1L)
                .regionName(RegionName.JUNGWON_GU)
                .detailedAddress("경기도 성남시 중원구 성남동 123")
                .managerNumber(10000001L)
                .build();
    }
}
