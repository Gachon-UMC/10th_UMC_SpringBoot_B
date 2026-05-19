package org.example.umc10thyongjae.domain.auth.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record SignUpRequestDto(
        @NotBlank(message = "uid 입력은 필수입니다.")
        String uid,

        @Valid
        List<TermsRequestDto> terms,
        @Valid
        UserInfoRequestDto userInfo,
        List<String> foodCate
) {
}
