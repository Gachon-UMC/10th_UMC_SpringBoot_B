package com.example.umc._th.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;

public class MemberResDTO {

    @Builder
    @Schema(name = "MemberGetInfoResponse")
    public record GetInfo(

        String name,
        String email,
        String phoneNum,
        Integer point,
        LocalDate birth,
        String address
    ){}

    @Schema(name = "MemberSignupResponse")
    public record Signup(
        Long id
    ){}

}
