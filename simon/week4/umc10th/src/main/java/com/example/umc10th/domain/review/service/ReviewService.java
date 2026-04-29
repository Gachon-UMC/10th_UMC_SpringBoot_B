package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.store.repository.StoreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public ReviewResDTO.CreateReview createReview(Long storeId, ReviewReqDTO.CreateReview dto) {
        return ReviewResDTO.CreateReview.builder()
                .reviewId(1L)
                .message("리뷰가 등록되었습니다.")
                .createdAt(LocalDateTime.now().toString())
                .build();
    }

    public ReviewResDTO.ReviewList getStoreReviews(Long storeId, int page, int size) {
        ReviewResDTO.ReviewItem item = ReviewResDTO.ReviewItem.builder()
                .reviewId(1L)
                .content("맛있어요")
                .score(4.5f)
                .reviewerNickname("홍길동")
                .createdAt(LocalDateTime.now().toString())
                .photoUrls(List.of("https://example.com/review-photo.jpg"))
                .build();

        return ReviewResDTO.ReviewList.builder()
                .reviews(List.of(item))
                .page(page)
                .size(size)
                .hasNext(false)
                .build();
    }
}
