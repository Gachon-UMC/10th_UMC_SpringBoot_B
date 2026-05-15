package com.example.umc10th.global.apiPayload.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class CursorResponse<T> {
    private List<T> data;
    private String nextCursor;
    private Boolean hasNext;

    public static <T> CursorResponse<T> of(List<T> data, String nextCursor, Boolean hasNext) {
        return CursorResponse.<T>builder()
                .data(data)
                .nextCursor(nextCursor)
                .hasNext(hasNext)
                .build();
    }
}