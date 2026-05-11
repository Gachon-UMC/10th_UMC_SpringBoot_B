package com.example.umc._th.global.dto;

import java.util.List;

public class PaginationDTO {

    public record OffsetPaginationDTO<T>(
            List<T> data,
            Integer pageNum,
            Integer pageSize
    ){}

    public record CursorPaginationDTO<T>(
            List<T> data,
            Boolean hasNext,
            Long nextCursor,
            Integer pageSize
    ){}
}
