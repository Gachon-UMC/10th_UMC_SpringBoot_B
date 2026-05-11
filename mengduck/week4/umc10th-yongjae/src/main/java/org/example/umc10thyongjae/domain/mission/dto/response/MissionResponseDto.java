package org.example.umc10thyongjae.domain.mission.dto.response;

import lombok.Builder;

@Builder
public record MissionResponseDto(
        long missionId,
        long storeId,
        String storeName,
        String storeCategory,
        int reward,
        String expireDate
) {
}

