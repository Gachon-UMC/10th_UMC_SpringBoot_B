package org.example.umc10thyongjae.domain.store.dto.response;

import lombok.Builder;

@Builder
public record StoreReviewResponseDto(
        long reviewId,
        long userId,
        String userName,
        long storeId,
        String storeName,
        String score,
        String content,
        String createdAt
) {
}
