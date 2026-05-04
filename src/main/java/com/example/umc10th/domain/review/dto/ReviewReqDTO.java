package com.example.umc10th.domain.review.dto;

import lombok.Getter;

@Getter
public class ReviewReqDTO {

    private Long userMissionId;
    private Integer rating;
    private String content;
}