package com.example.umc10th.domain.store.dto;

import lombok.Builder;

public class StoreResDTO {
    @Builder
    public record Detail(
            Long storeId,
            String storeName,
            Long regionId, // Added regionId field
            String regionName,
            String detailedAddress,
            Long managerNumber
    ) {}
}