package com.example.umc._th.domain.member.dto;

import lombok.Builder;

public class MemberResDTO {

    @Builder
    public record GetInfo(
        String name,
        String email,
        String phone,
        Integer point
    ){}

    public record Signup(
        Long id
    ){}

}
