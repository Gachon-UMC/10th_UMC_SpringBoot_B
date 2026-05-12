package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.exception.ReviewException;
import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

// 해당 클래스를 Service 계층 Bean으로 등록합니다.
@Service
// final 필드를 생성자 주입 방식으로 주입합니다.
@RequiredArgsConstructor
public class ReviewService {

    // 리뷰 Entity의 DB 접근을 담당하는 Repository입니다.
    private final ReviewRepository reviewRepository;

    // 회원 Entity의 DB 접근을 담당하는 Repository입니다.
    private final MemberRepository memberRepository;

    // 리뷰 작성 비즈니스 로직을 처리합니다.
    @Transactional
    public ReviewResDTO.CreateReviewDTO createReview(Long userId, ReviewReqDTO request) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Review review = ReviewConverter.toReview(request, member);
        Review savedReview = reviewRepository.save(review);

        return ReviewConverter.toReviewResDTO(savedReview);
    }

    // 내가 작성한 리뷰 목록을 커서 기반으로 조회합니다.
    public ReviewResDTO.CursorPagination<ReviewResDTO.MyReviewDTO> getMyReviews(
            Long userId,
            Integer pageSize,
            String cursor,
            String query
    ) {
        PageRequest pageRequest = PageRequest.of(0, pageSize);
        Slice<Review> reviewSlice = getReviewSlice(userId, cursor, query, pageRequest);

        return ReviewConverter.toMyReviewPagination(reviewSlice, query);
    }

    // query와 cursor 상태에 따라 적절한 Repository 조회를 선택합니다.
    private Slice<Review> getReviewSlice(Long userId, String cursor, String query, PageRequest pageRequest) {
        if ("id".equalsIgnoreCase(query)) {
            return getReviewSliceById(userId, cursor, pageRequest);
        }

        if ("star".equalsIgnoreCase(query)) {
            return getReviewSliceByStar(userId, cursor, pageRequest);
        }

        throw new ReviewException(ReviewErrorCode.QUERY_NOT_VALID);
    }

    // ID 내림차순 커서 조회를 처리합니다.
    private Slice<Review> getReviewSliceById(Long userId, String cursor, PageRequest pageRequest) {
        if (isFirstCursor(cursor)) {
            return reviewRepository.findMyReviewsOrderByIdDesc(userId, pageRequest);
        }

        Long reviewId = parseIdCursor(cursor);

        return reviewRepository.findMyReviewsByIdCursor(userId, reviewId, pageRequest);
    }

    // 별점 내림차순, ID 내림차순 커서 조회를 처리합니다.
    private Slice<Review> getReviewSliceByStar(Long userId, String cursor, PageRequest pageRequest) {
        if (isFirstCursor(cursor)) {
            return reviewRepository.findMyReviewsOrderByStarDesc(userId, pageRequest);
        }

        String[] cursorSplit = splitCursor(cursor);
        Float star = Float.parseFloat(cursorSplit[0]);
        Long reviewId = Long.parseLong(cursorSplit[1]);

        return reviewRepository.findMyReviewsByStarCursor(userId, star, reviewId, pageRequest);
    }

    // 첫 페이지 요청 커서인지 확인합니다.
    private boolean isFirstCursor(String cursor) {
        return cursor == null || "-1".equals(cursor);
    }

    // ID 커서에서 reviewId 값을 추출합니다.
    private Long parseIdCursor(String cursor) {
        String[] cursorSplit = splitCursor(cursor);

        return Long.parseLong(cursorSplit[1]);
    }

    // 기준값과 ID로 구성된 커서를 분리합니다.
    private String[] splitCursor(String cursor) {
        String[] cursorSplit = cursor.split(":");

        if (cursorSplit.length != 2) {
            throw new ReviewException(ReviewErrorCode.CURSOR_NOT_VALID);
        }

        return cursorSplit;
    }
}
