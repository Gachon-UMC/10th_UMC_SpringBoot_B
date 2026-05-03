package com.example.umc._th.domain.store.dto;

import com.example.umc._th.domain.store.enums.StoreType;

public class StoreDTO {
    public record StoreSummary(
        String name,
        StoreType category
    ){}
}

