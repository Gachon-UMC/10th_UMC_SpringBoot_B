package org.example.umc10thyongjae.domain.auth.dto.request;

import java.util.List;

public record SignUpRequestDto(
        String uid,
        List<TermsRequestDto> terms,
        UserInfoRequestDto userInfo,
        List<String> foodCate
) {
}
