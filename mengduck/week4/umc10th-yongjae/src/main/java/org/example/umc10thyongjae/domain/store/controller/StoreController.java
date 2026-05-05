package org.example.umc10thyongjae.domain.store.controller;

import lombok.RequiredArgsConstructor;
import org.example.umc10thyongjae.domain.store.dto.request.WriteReviewRequestDto;
import org.example.umc10thyongjae.domain.store.service.StoreService;
import org.example.umc10thyongjae.global.apiPayload.ApiResponse;
import org.example.umc10thyongjae.global.apiPayload.code.GeneralSuccessCode;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/{storeId}/review")
    public ApiResponse<Void> writeReview(
            @PathVariable long storeId,
            @RequestBody WriteReviewRequestDto dto,
            @RequestAttribute long userId
    ) {
        storeService.saveStoreReview(userId, storeId, dto);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }
}
