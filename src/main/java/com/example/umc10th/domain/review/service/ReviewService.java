package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public ReviewResDTO createReview(Long userId, ReviewReqDTO request) {

        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저 없음"));

        Review review = Review.builder()
                .member(member)
                .content(request.getContent())
                .star(request.getRating().floatValue())
                .build();

        reviewRepository.save(review);

        return new ReviewResDTO(review.getId());
    }
}