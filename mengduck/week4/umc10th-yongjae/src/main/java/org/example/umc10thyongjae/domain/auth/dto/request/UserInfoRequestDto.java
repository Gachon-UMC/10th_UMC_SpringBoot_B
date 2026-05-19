package org.example.umc10thyongjae.domain.auth.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.example.umc10thyongjae.domain.auth.enums.Gender;

public record UserInfoRequestDto(
        @NotBlank(message = "이름 입력은 필수입니다.")
        String name,
        @NotNull(message = "성별 입력은 필수입니다.")
        Gender gender,

        @Pattern(
                regexp = "^\\d{4}-\\d{2}-\\d{2}$",
                message = "생년월일은 yyyy-MM-dd 형식이어야 합니다."
        )
        String birthDate,

        @Pattern(
                regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
                message = "올바른 이메일 형식이어야 합니다."
        )
        String mail,

        @NotBlank(message = "전화번호 입력은 필수입니다.")
        String phoneNumber,

        @NotBlank(message = "주소 입력은 필수입니다.")
        String address,

        @NotBlank(message = "상세 주소 입력은 필수입니다.")
        String addressDetail
) {
}
