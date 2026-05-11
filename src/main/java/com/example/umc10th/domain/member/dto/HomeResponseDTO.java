package com.example.umc10th.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HomeResponseDTO {

    private String name;
    private String email;
    private String phoneNumber;
    private Integer point;
}