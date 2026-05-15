package com.example.umc10th.domain.store.converter;

import com.example.umc10th.domain.store.dto.StoreResDTO;
import com.example.umc10th.domain.store.entity.Store;
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

    // 목록 조회 변환
    public static StoreResDTO.StoreSummaryList toStoreSummaryList(Page<Store> storePage) {
        List<StoreResDTO.StoreSummary> list = storePage.getContent().stream()
                .map(store -> StoreResDTO.StoreSummary.builder()
                        .storeId(store.getId())
                        .storeName(store.getStoreName())
                        .detailAddress(store.getDetailAddress())
                        .build())
                .toList();

        return StoreResDTO.StoreSummaryList.builder()
                .storeList(list)
                .listSize(list.size())
                .totalPage(storePage.getTotalPages())
                .totalElements(storePage.getTotalElements())
                .isFirst(storePage.isFirst())
                .isLast(storePage.isLast())
                .build();
    }
}
