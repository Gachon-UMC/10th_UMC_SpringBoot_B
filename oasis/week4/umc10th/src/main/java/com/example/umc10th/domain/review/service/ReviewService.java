package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.entity.ReviewImage;
import com.example.umc10th.domain.review.exception.ReviewException;
import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.exception.StoreException;
import com.example.umc10th.domain.store.exception.code.StoreErrorCode;
import com.example.umc10th.domain.store.repository.StoreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    public ReviewResDTO.MyReviewListResponse getMyReviews(Long memberId, Long cursor, int size, String sortBy) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // size + 1 개를 조회하여 다음 페이지 존재 여부 확인
        PageRequest pageRequest = PageRequest.of(0, size + 1);
        List<Review> reviews;

        if ("rating".equals(sortBy)) {
            reviews = getReviewsSortedByRating(memberId, cursor, pageRequest);
        } else {
            reviews = getReviewsSortedById(memberId, cursor, pageRequest);
        }

        boolean hasNext = reviews.size() > size;
        if (hasNext) {
            reviews = reviews.subList(0, size);
        }

        return ReviewConverter.toMyReviewListResponse(reviews, hasNext);
    }

    private List<Review> getReviewsSortedById(Long memberId, Long cursor, PageRequest pageRequest) {
        if (cursor == null || cursor == 0) {
            return reviewRepository.findByMemberIdOrderByIdAsc(memberId, pageRequest);
        }
        return reviewRepository.findByMemberIdAndIdGreaterThanOrderByIdAsc(memberId, cursor, pageRequest);
    }

    private List<Review> getReviewsSortedByRating(Long memberId, Long cursor, PageRequest pageRequest) {
        if (cursor == null || cursor == 0) {
            return reviewRepository.findByMemberIdOrderByRatingDesc(memberId, pageRequest);
        }
        // cursor ID로 해당 리뷰의 rating 조회
        Review cursorReview = reviewRepository.findById(cursor)
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.REVIEW_NOT_FOUND));
        return reviewRepository.findByMemberIdOrderByRatingDescWithCursor(
                memberId, cursorReview.getRating(), cursorReview.getId(), pageRequest);
    }

    @Transactional
    public ReviewResDTO.CreateReviewResponse createReview(ReviewReqDTO.CreateReviewRequest request) {

        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Store store = storeRepository.findById(request.storeId())
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        Review review = ReviewConverter.toReview(request, member, store);
        review = reviewRepository.save(review);

        if (request.images() != null && !request.images().isEmpty()) {
            List<ReviewImage> reviewImages = ReviewConverter.toReviewImageList(request.images(), review);
            review.getReviewImageList().addAll(reviewImages);
        }

        return ReviewConverter.toCreateReviewResponse(review);
    }
}
