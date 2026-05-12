package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Reply;
import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Slice;

import java.time.format.DateTimeFormatter;
import java.util.List;

// 리뷰 도메인의 Entity와 DTO 변환을 담당합니다.
public class ReviewConverter {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd");

    // 리뷰 작성 요청 DTO와 회원 Entity를 리뷰 Entity로 변환합니다.
    public static Review toReview(ReviewReqDTO request, Member member) {
        return Review.builder()
                .member(member)
                .content(request.getContent())
                .star(request.getRating().floatValue())
                .build();
    }

    // 리뷰 Entity를 리뷰 작성 응답 DTO로 변환합니다.
    public static ReviewResDTO.CreateReviewDTO toReviewResDTO(Review review) {
        return ReviewResDTO.CreateReviewDTO.builder()
                .reviewId(review.getId())
                .build();
    }

    // 리뷰 Slice를 커서 기반 페이지네이션 응답 DTO로 변환합니다.
    public static ReviewResDTO.CursorPagination<ReviewResDTO.MyReviewDTO> toMyReviewPagination(
            Slice<Review> reviewSlice,
            String query
    ) {
        List<ReviewResDTO.MyReviewDTO> reviews = reviewSlice.getContent().stream()
                .map(ReviewConverter::toMyReviewDTO)
                .toList();

        return ReviewResDTO.CursorPagination.<ReviewResDTO.MyReviewDTO>builder()
                .data(reviews)
                .hasNext(reviewSlice.hasNext())
                .nextCursor(createNextCursor(reviewSlice, query))
                .pageSize(reviewSlice.getSize())
                .build();
    }

    // 리뷰 Entity를 내가 작성한 리뷰 응답 DTO로 변환합니다.
    private static ReviewResDTO.MyReviewDTO toMyReviewDTO(Review review) {
        return ReviewResDTO.MyReviewDTO.builder()
                .reviewId(review.getId())
                .nickname(review.getMember().getName())
                .star(review.getStar())
                .content(review.getContent())
                .createdAt(review.getCreatedAt().format(DATE_FORMATTER))
                .reply(toReplyDTO(review.getReplies().stream().findFirst().orElse(null)))
                .build();
    }

    // Reply Entity를 답글 응답 DTO로 변환합니다.
    private static ReviewResDTO.ReplyDTO toReplyDTO(Reply reply) {
        if (reply == null) {
            return null;
        }

        return ReviewResDTO.ReplyDTO.builder()
                .content(reply.getContent())
                .createdAt(reply.getCreatedAt().format(DATE_FORMATTER))
                .build();
    }

    // 마지막 데이터 기준으로 다음 커서를 생성합니다.
    private static String createNextCursor(Slice<Review> reviewSlice, String query) {
        if (!reviewSlice.hasNext() || reviewSlice.getContent().isEmpty()) {
            return null;
        }

        Review lastReview = reviewSlice.getContent().get(reviewSlice.getContent().size() - 1);

        if ("star".equalsIgnoreCase(query)) {
            return lastReview.getStar() + ":" + lastReview.getId();
        }

        return lastReview.getId() + ":" + lastReview.getId();
    }
}
