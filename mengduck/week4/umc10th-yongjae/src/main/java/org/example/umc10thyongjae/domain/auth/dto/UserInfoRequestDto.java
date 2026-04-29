package org.example.umc10thyongjae.domain.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.umc10thyongjae.domain.auth.dto.enums.Gender;

public record UserInfoRequestDto(
        String name,
        Gender gender,
        @JsonProperty("birth_date")
        String birthDate,
        String mail,
        @JsonProperty("phone_number")
        String phoneNumber,

        String address,

        @JsonProperty("address_detail")
        String addressDetail
) {
}
