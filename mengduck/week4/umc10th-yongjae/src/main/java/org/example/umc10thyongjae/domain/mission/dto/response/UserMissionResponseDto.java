package org.example.umc10thyongjae.domain.mission.dto.response;

import lombok.Builder;
import org.example.umc10thyongjae.domain.mission.enums.MissionStatus;

@Builder
public record UserMissionResponseDto(
        long missionKey,
        long storeKey,
        String storeName,
        String storeCategory,
        int reward,
        String expireDate,
        MissionStatus status
) {
}

