package com.example.umc10th.domain.auth.dto;

import com.example.umc10th.domain.user.enums.Gender;

import java.time.LocalDate;
import java.util.List;

public class AuthReqDto {

    public record Signup(
            String email,
            String password,
            String name,
            Gender gender,
            LocalDate birth,
            String address,
            String detailAddress,
            String phoneNumber,
            String nickName,
            int point,
            List<Long> termIds,
            List<Long> foodIds
    ){}
}
