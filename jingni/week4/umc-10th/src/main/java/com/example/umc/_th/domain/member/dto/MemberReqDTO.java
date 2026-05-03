package com.example.umc._th.domain.member.dto;

import com.example.umc._th.domain.member.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MemberReqDTO {

    @Schema(name = "MemberSignupRequest")
    // 회원가입
    public record Signup (
        String name,
        String email,
        Gender gender,
        LocalDate birth,
        String address,
        List<Long> favoriteFoodIds

    ){}
}

