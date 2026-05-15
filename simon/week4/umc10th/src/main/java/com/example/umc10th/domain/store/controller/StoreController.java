package com.example.umc10th.domain.store.controller;

import com.example.umc10th.domain.store.dto.StoreResDTO;
import com.example.umc10th.domain.store.exception.code.StoreSuccessCode;
import com.example.umc10th.domain.store.service.StoreService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.dto.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stores")
public class StoreController {
    private final StoreService storeService;

    // 가게 상세 정보 조회
    @GetMapping("/{storeId}")
    public ApiResponse<StoreResDTO.GetStoreInfo> getStoreInfo(@PathVariable Long storeId) {
        return ApiResponse.onSuccess(StoreSuccessCode.OK, storeService.getStoreInfo(storeId));
    }

    // 특정 지역의 가게 목록 조회
    @GetMapping("/regions/{regionId}")
    public ApiResponse<PageResponse<StoreResDTO.StoreSummary>> getStoreList(
            @PathVariable Long regionId,
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return ApiResponse.onSuccess(StoreSuccessCode.GET_LIST_OK,
                storeService.getStoreListByRegion(regionId, pageable));
    }
}
