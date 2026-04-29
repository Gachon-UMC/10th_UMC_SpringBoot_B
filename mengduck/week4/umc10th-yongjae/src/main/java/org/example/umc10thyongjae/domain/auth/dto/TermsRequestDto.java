package org.example.umc10thyongjae.domain.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TermsRequestDto(
        @JsonProperty("terms_id")
        String termsId,

        Boolean value
) {
}
