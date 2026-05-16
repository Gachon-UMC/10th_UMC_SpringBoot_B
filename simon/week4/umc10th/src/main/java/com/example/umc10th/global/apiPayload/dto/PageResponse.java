package com.example.umc10th.global.apiPayload.dto;

import lombok.Builder;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder
public record PageResponse<T>(
        List<T> data,
        Integer pageNumber,
        Integer pageSize,
        Integer totalPage,
        Long totalElements,
        Boolean isFirst,
        Boolean isLast
) {
    public static <T, E> PageResponse<T> of(Page<E> page, List<T> data) {
        return PageResponse.<T>builder()
                .data(data)
                .pageNumber(page.getNumber() + 1)
                .pageSize(page.getSize())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }
}
