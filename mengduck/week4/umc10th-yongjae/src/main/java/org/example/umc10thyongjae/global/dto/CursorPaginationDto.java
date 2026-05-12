package org.example.umc10thyongjae.global.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record CursorPaginationDto<T>(
        String nextCursor,
        Integer size,

        Boolean hasNext,

        List<T> data
) { }