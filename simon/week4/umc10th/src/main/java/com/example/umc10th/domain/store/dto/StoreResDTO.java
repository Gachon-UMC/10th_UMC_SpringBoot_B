package com.example.umc10th.domain.store.dto;

import com.example.umc10th.domain.store.enums.RegionName;
import lombok.Builder;

public class StoreResDTO {
    @Builder
    public record GetStoreDetail(
            Long storeId,
            String storeName,
            Long regionId, // Added regionId field
            RegionName regionName,
            String detailedAddress,
            Long managerNumber
    ) {}
}