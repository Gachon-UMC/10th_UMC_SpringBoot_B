package org.example.umc10thyongjae.domain.auth.dto.request;


import org.example.umc10thyongjae.domain.auth.enums.Gender;

public record UserInfoRequestDto(
        String name,
        Gender gender,
        String birthDate,
        String mail,
        String phoneNumber,
        String address,
        String addressDetail
) {
}
