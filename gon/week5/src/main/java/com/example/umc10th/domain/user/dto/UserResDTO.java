package com.example.umc10th.domain.user.dto;

import lombok.Builder;

public class UserResDTO {

    @Builder
    public record GetInfo(
            Long id,
            String name,
            String profileUrl,
            String email,
            String phoneNumber,
            Integer point
    ){}
}
