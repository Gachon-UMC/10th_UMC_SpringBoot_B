package com.example.umc10th.domain.store.dto;

import com.example.umc10th.domain.food_category.enums.FoodCategoryName;
import com.example.umc10th.domain.store.enums.RegionName;
import lombok.Builder;

public class StoreResDTO {
    @Builder
    public record GetStoreInfo(
            Long storeId,
            String storeName,
            Long regionId,
            RegionName regionName,
            FoodCategoryName foodCategoryName,
            String detailAddress
    ) {}

    @Builder
    public record StoreSummary(
            Long storeId,
            String storeName,
            String detailAddress
    ) {}
}