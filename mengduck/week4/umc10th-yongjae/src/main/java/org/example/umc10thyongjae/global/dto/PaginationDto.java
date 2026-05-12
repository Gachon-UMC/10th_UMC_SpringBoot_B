package org.example.umc10thyongjae.global.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record PaginationDto<T>(
        List<T> data,
        Integer page,
        Integer size,

        Boolean hasNext
) { }
