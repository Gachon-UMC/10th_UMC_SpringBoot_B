package com.example.umc._th.domain.member.dto;

import com.example.umc._th.domain.member.enums.Gender;

import java.util.ArrayList;

public class MemberReqDTO {

    // 회원가입
    public record Signup (
        String name,
        String email,
        Gender gender,
        String birth,
        String address,
        ArrayList<Integer> favoriteFoodIds

    ){}
}

