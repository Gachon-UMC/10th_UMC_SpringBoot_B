package com.example.umc._th.domain.review.service;

import com.example.umc._th.domain.member.entity.Member;
import com.example.umc._th.domain.member.exception.MemberException;
import com.example.umc._th.domain.member.exception.code.MemberErrorCode;
import com.example.umc._th.domain.member.repository.MemberRepository;
import com.example.umc._th.domain.mission.entity.Mission;
import com.example.umc._th.domain.review.converter.ReviewConverter;
import com.example.umc._th.domain.review.dto.ReviewReqDTO;
import com.example.umc._th.domain.review.dto.ReviewResDTO;
import com.example.umc._th.domain.review.entity.Review;
import com.example.umc._th.domain.review.exception.ReviewException;
import com.example.umc._th.domain.review.repository.ReviewRepository;
import com.example.umc._th.domain.store.entity.Store;
import com.example.umc._th.domain.store.exception.StoreException;
import com.example.umc._th.domain.store.exception.code.ReviewErrorCode;
import com.example.umc._th.domain.store.exception.code.StoreErrorCode;
import com.example.umc._th.domain.store.repository.StoreRepository;
import com.example.umc._th.global.dto.PaginationDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public ReviewResDTO.CreateReview createReview (
            Long storeId,
            Long memberId,
            ReviewReqDTO.CreateReview request
    ){

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Review review = ReviewConverter.toEntity(request, store, member);

        Review saved = reviewRepository.save(review);

        return ReviewConverter.toCreateReviewDTO(saved);
    }

    @Transactional()
    public PaginationDTO.CursorPaginationDTO<ReviewResDTO.ReviewInfo> getMyReviews(
            Long memberId,
            Integer pageSize,
            Long cursor
    ) {

        if (!memberRepository.existsById(memberId)) {
            throw new MemberException(MemberErrorCode.MEMBER_NOT_FOUND);
        }

        Pageable pageable = PageRequest.of(0, pageSize + 1);
        Slice<Review> reviews = reviewRepository.findMyReviews(
                memberId,
                cursor,
                pageable
        );

        boolean hasNext = reviews.hasNext();

        List<Review> review = reviews.getContent();

        if (review.size() > pageSize) {
            review = review.subList(0, pageSize);
        }

        Long nextCursor = hasNext
                ? review.get(review.size() - 1).getId()
                : null;

        List<ReviewResDTO.ReviewInfo> result = review.stream()
                .map(ReviewConverter::toReviewInfo)
                .toList();

        return new PaginationDTO.CursorPaginationDTO<>(
                result,
                hasNext,
                nextCursor,
                pageSize
        );
    }
    }