package com.example.umc10th.domain.store.converter;

import com.example.umc10th.domain.store.dto.StoreResDTO;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.global.apiPayload.dto.PageResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public class StoreConverter {
    // 단일 조회 변환
    public static StoreResDTO.GetStoreInfo toGetStoreInfo(Store store) {
        return StoreResDTO.GetStoreInfo.builder()
                .storeId(store.getId())
                .storeName(store.getStoreName())
                .regionId(store.getRegion().getId())
                .regionName(store.getRegion().getRegionName())
                .foodCategoryName(store.getFoodCategory().getFoodCategoryName())
                .detailAddress(store.getDetailAddress())
                .build();
    }

    // 특정 지역의 가게 목록을 전역 페이징 규격으로 변환
    public static PageResponse<StoreResDTO.StoreSummary> toStoreSummaryPage(Page<Store> storePage) {
        List<StoreResDTO.StoreSummary> data = storePage.getContent().stream()
                .map(store -> StoreResDTO.StoreSummary.builder()
                        .storeId(store.getId())
                        .storeName(store.getStoreName())
                        .detailAddress(store.getDetailAddress())
                        .build())
                .toList();

        return PageResponse.of(storePage, data);
    }
}
