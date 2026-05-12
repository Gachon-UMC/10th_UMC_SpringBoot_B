package org.example.umc10thyongjae.domain.mission.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ReqMissionCompleteRequestDto(
        @NotBlank(message = "핀코드 입력은 필수입니다.")
        String pinCode
) {
}
