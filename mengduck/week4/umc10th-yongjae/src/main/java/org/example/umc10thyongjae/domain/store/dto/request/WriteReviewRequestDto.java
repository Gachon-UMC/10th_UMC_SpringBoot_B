package org.example.umc10thyongjae.domain.store.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record WriteReviewRequestDto(
        @NotBlank(message = "평점은 필수입니다.")
        String score,

        @NotBlank(message = "리뷰 내용은 필수입니다.")
        @Size(min = 5, message = "리뷰 내용은 최소 5자 이상이어야 합니다.")
        String comment,

        List<String> imgUrlList
) {}