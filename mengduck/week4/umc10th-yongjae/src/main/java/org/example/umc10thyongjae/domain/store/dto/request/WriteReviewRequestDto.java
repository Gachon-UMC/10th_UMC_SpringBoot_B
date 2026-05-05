package org.example.umc10thyongjae.domain.store.dto.request;

import java.util.List;

public record WriteReviewRequestDto(
        String score,
        String comment,

        List<String> imgUrlList
) {}