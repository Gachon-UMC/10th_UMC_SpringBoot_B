package com.example.umc10th.domain.member.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class MemberReqDTO {

    public record LoginRequest(
            @NotBlank(message = "이메일은 필수입니다.")
            @Email(message = "올바른 이메일 형식이어야 합니다.")
            String email,

            @NotBlank(message = "비밀번호는 필수입니다.")
            String password
    ) {}

    public record SignupRequest(
            @NotBlank(message = "이메일은 필수입니다.")
            @Email(message = "올바른 이메일 형식이어야 합니다.")
            String email,

            @NotBlank(message = "비밀번호는 필수입니다.")
            @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하여야 합니다.")
            String password,

            @NotBlank(message = "닉네임은 필수입니다.")
            @Size(min = 1, max = 20, message = "닉네임은 1자 이상 20자 이하여야 합니다.")
            String nickname,

            @NotBlank(message = "성별은 필수입니다.")
            String gender,

            @NotNull(message = "생년월일은 필수입니다.")
            LocalDate birth,

            @NotBlank(message = "주소는 필수입니다.")
            String address
    ) {}
}
