package org.example.umc10thyongjae.domain.mission.dto.response;

import lombok.Builder;

@Builder
public record ReqMissionCompleteResponseDto(
        String pinCode
) {
}
