package org.example.umc10thyongjae.domain.auth.dto.response;

import lombok.Builder;

@Builder
public record UserInfoResponseDto(
        String name,
        String mail,
        String phoneNumber,
        Boolean phoneNumberVerified,

        int point
) {
}
