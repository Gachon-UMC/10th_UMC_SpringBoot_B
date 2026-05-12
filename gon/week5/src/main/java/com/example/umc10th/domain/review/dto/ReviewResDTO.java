package com.example.umc10th.domain.review.dto;

import lombok.Builder;

import java.util.List;

public class ReviewResDTO {


    @Builder
    public record CursorPagination<T>(
            List<T> data,
            Boolean hasNext,
            String nextCusor,
            int size
    ){}

    @Builder
    public record CreateReview(
            Long reviewId,
            Long userId,
            Long storeId,
            String reviewComment,
            int star
    ){}

    @Builder
    public record GetReview(
            Long reviewId,
            Long userId,
            String reviewComment,
            int star
    ){}

}
