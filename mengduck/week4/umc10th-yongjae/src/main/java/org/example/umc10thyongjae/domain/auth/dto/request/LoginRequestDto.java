package org.example.umc10thyongjae.domain.auth.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDto(
        @NotBlank(message = "아이디 입력은 필수입니다.")
        String id,

        @NotBlank(message = "비밀번호 입력은 필수입니다.")
        String pwd
) {
}
