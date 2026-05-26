package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDate;

// 회원가입 요청 정보를 담는 DTO입니다.
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

    // 회원 성별입니다.
    @NotNull(message = "성별은 필수입니다.")
    private Gender gender;

    // 회원 생년월일입니다.
    private LocalDate birth;

    // 회원 주소입니다.
    @NotBlank(message = "주소는 필수입니다.")
    @Size(max = 255, message = "주소는 255자 이하여야 합니다.")
    private String address;

    // 회원 상세 주소입니다.
    private String detailAddress;

    // 회원 전화번호입니다.
    @Pattern(regexp = "^$|^\\d{11}$", message = "전화번호는 숫자 11자리여야 합니다.")
    private String phoneNumber;

    // 회원 프로필 이미지 URL입니다.
    private String profileUrl;
}
