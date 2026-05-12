package com.example.umc10th.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

// 요청 DTO 필드 조회를 위한 Getter를 생성합니다.
@Getter
public class UserJoinRequestDTO {

    // 회원 이메일입니다.
    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    // 회원 비밀번호입니다.
    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;

    // 회원 닉네임입니다.
    @NotBlank(message = "닉네임은 필수입니다.")
    @Size(max = 5, message = "닉네임은 5자 이하여야 합니다.")
    private String nickname;
}
