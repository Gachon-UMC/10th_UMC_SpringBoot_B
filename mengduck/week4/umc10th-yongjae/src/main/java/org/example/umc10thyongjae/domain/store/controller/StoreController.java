package org.example.umc10thyongjae.domain.store.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.umc10thyongjae.domain.store.dto.request.WriteReviewRequestDto;
import org.example.umc10thyongjae.domain.store.dto.response.StoreReviewResponseDto;
import org.example.umc10thyongjae.domain.store.enums.StoreReviewSortType;
import org.example.umc10thyongjae.domain.store.service.StoreReviewService;
import org.example.umc10thyongjae.domain.store.service.StoreService;
import org.example.umc10thyongjae.global.apiPayload.ApiResponse;
import org.example.umc10thyongjae.global.apiPayload.code.GeneralSuccessCode;
import org.example.umc10thyongjae.global.dto.CursorPaginationDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

    private final StoreReviewService storeReviewService;

    @PostMapping("/{storeId}/review")
    public ApiResponse<Void> writeReview(
            @PathVariable long storeId,
            @RequestBody @Valid WriteReviewRequestDto dto,
            @RequestAttribute long userId
    ) {
        storeService.saveStoreReview(userId, storeId, dto);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }

    @GetMapping("/review/my")
    public ApiResponse<CursorPaginationDto<StoreReviewResponseDto>> getMyReviewList(
            @RequestAttribute long userId,
            @RequestParam(required = false) Long cursor,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "LATEST") StoreReviewSortType sort
    ) {
        CursorPaginationDto<StoreReviewResponseDto> result = storeReviewService.getMyReviewList(userId, cursor, size, sort);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}
