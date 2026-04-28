package com.example.umc10th.domain.store.service;

import com.example.umc10th.domain.store.dto.StoreResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {
    public StoreResDTO.Detail getStoreDetail(Long storeId) {
        return StoreResDTO.Detail.builder()
                .storeId(storeId)
                .storeName("가게이름")
                .regionId(1L)
                .detailedAddress("경기도 성남시 중원구 성남동 123")
                .managerNumber(10000001L)
                .build();
    }
}
