package org.example.umc10thyongjae.domain.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TermsRequestDto(
        @NotBlank(message = "약관 ID값은 필수입니다.")
        String termsId,

        @NotNull(message = "약관 동의 여부는 필수입니다.")
        Boolean value
) {
}
