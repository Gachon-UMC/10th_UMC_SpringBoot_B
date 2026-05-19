package org.example.umc10thyongjae.domain.auth.dto.request;

import jakarta.validation.constraints.NotNull;
import org.example.umc10thyongjae.domain.auth.enums.TermName;

public record TermsRequestDto(
        @NotNull(message = "약관 이름은 필수입니다.")
        TermName termName,

        @NotNull(message = "약관 동의 여부는 필수입니다.")
        Boolean value
) {
}
