package com.example.umc10th.domain.member.dto;

import lombok.Getter;

// 요청 DTO 필드 조회를 위한 Getter를 생성합니다.
@Getter
public class ReviewRequestDTO {

    // 리뷰 내용입니다.
    private String content;

    // 리뷰 별점입니다.
    private Integer rating;
}
