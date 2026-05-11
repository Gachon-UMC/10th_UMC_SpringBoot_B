package com.example.umc10th.domain.member.dto;

import lombok.Getter;

@Getter
public class ReviewRequestDTO {

    private Long userMissionId;
    private Integer rating;
    private String content;
}