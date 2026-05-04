package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.entity.ReviewPhoto;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.exception.StoreException;
import com.example.umc10th.domain.store.exception.code.StoreErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.store.repository.StoreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public ReviewResDTO.CreateReview createReview(Long storeId, ReviewReqDTO.CreateReview dto) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.MEMBER_NOT_FOUND));
        var member = memberRepository.findById(dto.memberId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Review review = Review.builder()
                .member(member)
                .store(store)
                .content(dto.content())
                .score(dto.score())
                .reviewPhotoList(new ArrayList<>())
                .replyList(new ArrayList<>())
                .build();

        if (dto.photoUrls() != null) {
            for (String url : dto.photoUrls()) {
                review.getReviewPhotoList().add(ReviewPhoto.builder()
                        .review(review)
                        .reviewPhotoUrl(url)
                        .build());
            }
        }

        Review saved = reviewRepository.save(review);

        return ReviewResDTO.CreateReview.builder()
                .reviewId(saved.getId())
                .message("리뷰가 등록되었습니다.")
                .createdAt(saved.getCreatedAt())
                .build();
    }

    public ReviewResDTO.ReviewList getStoreReviews(Long storeId, int page, int size) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.MEMBER_NOT_FOUND));

        Page<Review> reviewPage = reviewRepository.findByStoreIdOrderByCreatedAtDesc(
                store.getId(),
                PageRequest.of(page, size)
        );

        List<ReviewResDTO.ReviewItem> items = reviewPage.getContent().stream()
                .map(review -> ReviewResDTO.ReviewItem.builder()
                        .reviewId(review.getId())
                        .content(review.getContent())
                        .score(review.getScore())
                        .reviewerNickname(review.getMember().getName())
                        .createdAt(review.getCreatedAt())
                        .photoUrls(review.getReviewPhotoList().stream()
                                .map(ReviewPhoto::getReviewPhotoUrl)
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());

        return ReviewResDTO.ReviewList.builder()
                .reviews(items)
                .page(reviewPage.getNumber())
                .size(reviewPage.getSize())
                .hasNext(reviewPage.hasNext())
                .build();
    }
}
