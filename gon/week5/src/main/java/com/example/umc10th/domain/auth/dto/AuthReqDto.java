package com.example.umc10th.domain.auth.dto;

import com.example.umc10th.domain.user.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import  jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public class AuthReqDto {

    public record Signup(

            @NotBlank(message="이메일은 필수입니다.")
            @Email(message = "이메일 형식이 올바르지 않습니다.")
            String email,

            @NotBlank(message = "비밀번호는 필수입니다.")
            @Size(min =8,max=20,message="비밀번호는 8자 이상 20자 이하로 입력해야 합니다")
            String password,

            @NotBlank(message="이름은 필수입니다.")
            String name,

            @NotNull(message="성별은 필수입니다.")
            Gender gender,

            @NotNull(message ="생년월일은 필수입니다.")
            LocalDate birth,

            @NotBlank(message = "주소는 필수입니다.")
            String address,

            @NotBlank(message = "상세주소는 필수입니다.")
            String detailAddress,

            @NotBlank(message = "전화번호는 필수입니다.")
            String phoneNumber,

            @NotBlank(message = "닉네임은 필수입니다.")
            @Size(min=1,max=10,message="닉네임은 1자 이상 10자 이하로 입력해야 합니다.")
            String nickName,

            int point,

            List<Long> termIds,
            List<Long> foodIds
    ){}

    public record LoginResult(
            @Email(message="이메일 형식을 다시 확인해주세요")
            @NotBlank(message="이메일 입력을 해주세요")
            String Email,
            @NotBlank(message="비밀번호 입력을 해주세요")
            String password
    ){}
}
