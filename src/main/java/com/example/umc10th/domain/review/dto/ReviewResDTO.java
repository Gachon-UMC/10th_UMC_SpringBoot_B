package com.example.umc10th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

// 리뷰 도메인 응답 DTO를 관리합니다.
public class ReviewResDTO {

    // 리뷰 작성 응답 DTO입니다.
    @Getter
    @Builder
    @AllArgsConstructor
    public static class CreateReviewDTO {

        // 생성된 리뷰 ID입니다.
        private Long reviewId;
    }

    // 커서 기반 페이지네이션 응답 DTO입니다.
    @Getter
    @Builder
    @AllArgsConstructor
    public static class CursorPagination<T> {

        // 현재 커서 페이지의 데이터 목록입니다.
        private List<T> data;

        // 다음 페이지 존재 여부입니다.
        private Boolean hasNext;

        // 다음 페이지 조회에 사용할 커서입니다.
        private String nextCursor;

        // 현재 페이지 크기입니다.
        private Integer pageSize;
    }

    // 내가 작성한 리뷰 조회 응답 DTO입니다.
    @Getter
    @Builder
    @AllArgsConstructor
    public static class MyReviewDTO {

        // 리뷰 ID입니다.
        private Long reviewId;

        // 작성자 닉네임입니다.
        private String nickname;

        // 리뷰 별점입니다.
        private Float star;

        // 리뷰 내용입니다.
        private String content;

        // 리뷰 작성일입니다.
        private String createdAt;

        // 사장님 답글입니다.
        private ReplyDTO reply;
    }

    // 리뷰 답글 응답 DTO입니다.
    @Getter
    @Builder
    @AllArgsConstructor
    public static class ReplyDTO {

        // 답글 내용입니다.
        private String content;

        // 답글 작성일입니다.
        private String createdAt;
    }
}
