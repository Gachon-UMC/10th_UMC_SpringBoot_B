package com.example.umc10th.domain.member.dto;

import java.time.LocalDate;

public class MemberReqDTO {

    public record SignupRequest(
            String email,
            String password,
            String nickname,
            String gender,
            LocalDate birth,
            String address
    ) {}
}
