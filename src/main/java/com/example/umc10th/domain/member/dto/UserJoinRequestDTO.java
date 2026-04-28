package com.example.umc10th.domain.member.dto;

import lombok.Getter;

@Getter
public class UserJoinRequestDTO {

    private String email;
    private String password;
    private String nickname;
}