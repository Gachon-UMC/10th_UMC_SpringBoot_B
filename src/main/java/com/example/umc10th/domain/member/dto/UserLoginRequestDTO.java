package com.example.umc10th.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

// 로그인 요청 정보를 담는 DTO입니다.
@Getter
public class UserLoginRequestDTO {

    // 로그인에 사용할 이메일입니다.
    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    // 로그인에 사용할 비밀번호입니다.
    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;
}
