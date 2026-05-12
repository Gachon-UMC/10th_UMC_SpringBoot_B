package com.example.umc10th.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

// 응답 DTO 필드 조회를 위한 Getter를 생성합니다.
@Getter
// 응답 DTO 생성 시 builder 패턴을 사용할 수 있게 합니다.
@Builder
// 모든 필드를 받는 생성자를 생성합니다.
@AllArgsConstructor
public class HomeResponseDTO {

    // 회원 이름입니다.
    private String name;

    // 회원 이메일입니다.
    private String email;

    // 회원 전화번호입니다.
    private String phoneNumber;

    // 회원 포인트입니다.
    private Integer point;
}
