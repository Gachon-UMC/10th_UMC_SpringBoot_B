package com.example.umc10th.domain.auth.dto;

import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.member.enums.SocialType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class AuthReqDTO {
    public record Register(
            @NotBlank(message = "이메일은 필수 입력 사항입니다.")
            @Email(message = "올바른 이메일 형식이 아닙니다.")
            String email,

            @NotBlank(message = "비밀번호는 필수 입력 사항입니다.")
            @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
            String password,

            @NotBlank(message = "이름은 필수 입력 사항입니다.")
            String name,

            @NotNull(message = "성별을 선택해주세요.")
            Gender gender,

            @NotNull(message = "생년월일을 입력해주세요.")
            LocalDate birthDate,
            String socialUid,

            @NotNull(message = "가입 유형(SocialType)은 필수입니다.")
            SocialType socialType,

            @NotBlank(message = "전화번호는 필수 입력 사항입니다.")
            String phoneNumber,

            @NotBlank(message = "주소는 필수 입력 사항입니다.")
            String address,
            String detailAddress,
            String profileUrl
    ) {}

    public record Login(
            @NotBlank(message = "이메일을 입력해주세요.")
            @Email(message = "올바른 이메일 형식이 아닙니다.")
            String email,

            @NotBlank(message = "비밀번호를 입력해주세요.")
            String password
    ) {}
}
