package org.example.umc10thyongjae.domain.mission.dto.response;

import lombok.Builder;

@Builder
public record MissionResponseDto(
        long missionKey,
        long storeKey,
        String storeName,
        String storeCategory,
        int reward,
        int rewardCondition,
        String expireDate
) {
}

