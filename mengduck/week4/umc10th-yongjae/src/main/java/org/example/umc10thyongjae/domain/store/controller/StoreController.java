package org.example.umc10thyongjae.domain.store.controller;

import org.example.umc10thyongjae.domain.store.dto.request.WriteReviewRequestDto;
import org.example.umc10thyongjae.global.apiPayload.ApiResponse;
import org.example.umc10thyongjae.global.apiPayload.code.GeneralSuccessCode;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store")
public class StoreController {

    @PostMapping("/{storeKey}/review")
    public ApiResponse<Void> writeReview(
            @PathVariable long storeKey,
            @RequestBody WriteReviewRequestDto dto,
            @RequestAttribute int userKey
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }
}
