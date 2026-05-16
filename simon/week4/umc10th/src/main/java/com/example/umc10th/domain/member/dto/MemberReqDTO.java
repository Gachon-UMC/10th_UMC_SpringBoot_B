package com.example.umc10th.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class MemberReqDTO {
    public record UpdateInfo(
            @NotBlank(message = "전화번호는 필수 입력 사항입니다.")
            @Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", message = "올바른 전화번호 형식이 아닙니다.")
            String phoneNumber,

            String profileUrl
    ) {}
}
