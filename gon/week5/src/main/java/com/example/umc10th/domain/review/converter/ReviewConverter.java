package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.user.entity.User;

import java.util.List;

public class ReviewConverter {

    public static <T> ReviewResDTO.CursorPagination<T> toCursorPagination(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            int size
    ){
        return ReviewResDTO.CursorPagination.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCusor(nextCursor)
                .size(size)
                .build();
    }

    public static ReviewResDTO.CreateReview tocreateReview(
            Review review
    ){
        return ReviewResDTO.CreateReview.builder()
                .reviewId(review.getId())
                .userId(review.getUser().getId())
                .storeId(review.getStore().getId())
                .reviewComment(review.getReviewComment())
                .star(review.getStar())
                .build();
    }

    public static ReviewResDTO.GetReview toGetReview(
            Review review
    ){
        User user = review.getUser();
        return ReviewResDTO.GetReview.builder()
                .reviewId(review.getId())
                .userId(user.getId())
                .reviewComment(review.getReviewComment())
                .star(review.getStar())
                .build();
    }
}
