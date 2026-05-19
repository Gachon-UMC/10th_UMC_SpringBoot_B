package com.example.umc._th.domain.member.dto;

import com.example.umc._th.domain.member.enums.Gender;
import com.example.umc._th.domain.member.enums.SocialType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    @Builder
    @Schema(name = "MemberSignupRequest")
    // 회원가입
    public record Signup (

        @NotBlank(message = "이름은 빈칸일 수 없습니다.")
        String name,
        @NotBlank(message = "이메일은 빈칸일 수 없습니다.")
        @Email(message = "올바른 이메일 형식이 아닙니다.")
        String email,
        @NotBlank(message = "비밀번호는 빈칸일 수 없습니다.")
        String password,
        @NotBlank(message = "전화번호는 빈칸일 수 없습니다.")
        String phoneNum,
        @NotNull(message = "성별은 필수 값입니다.")
        Gender gender,
        @NotNull(message = "생년월일은 필수 값입니다.")
        LocalDate birth,
        @NotBlank(message = "주소는 빈칸일 수 없습니다.")
        String address,
        @NotNull(message = "소셜 로그인 종류는 필수 값입니다.")
        SocialType socialType,
        @NotEmpty(message = "선호 음식은 최소 1개 이상 선택해야 합니다.")
        List<Long> favoriteFoodIds

    ){}

    @Schema(name="TestMemberIdRequest")
    public record TestMemberIdRequest(
        @NotNull(message = "사용자 아이디는 필수 입력 값입니다.")
        Long id
    ){}
}

