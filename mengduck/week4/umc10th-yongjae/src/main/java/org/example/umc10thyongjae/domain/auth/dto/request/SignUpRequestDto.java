package org.example.umc10thyongjae.domain.auth.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record SignUpRequestDto(
        @NotBlank(message = "아이디 입력은 필수입니다.")
        String id,

        @NotBlank(message = "비밀번호 입력은 필수입니다.")
        String pwd,

        @Valid
        List<TermsRequestDto> terms,

        @NotNull(message = "회원 정보 입력은 필수입니다.")
        @Valid
        UserInfoRequestDto userInfo,

        List<String> foodCate
) {
}
