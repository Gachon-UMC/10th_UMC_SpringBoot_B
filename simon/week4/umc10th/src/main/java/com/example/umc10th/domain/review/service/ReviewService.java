package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.entity.ReviewPhoto;
import com.example.umc10th.domain.review.exception.ReviewException;
import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.exception.StoreException;
import com.example.umc10th.domain.store.repository.StoreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    // 리뷰 작성, 사용자가 특정 가게에 대한 리뷰를 등록
    @Transactional
    public ReviewResDTO.CreateReview createReview(Long storeId, ReviewReqDTO.CreateReview dto) {
        // 가게 or 사용자 존재 여부 확인
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(ReviewErrorCode.STORE_NOT_FOUND));

        var member = memberRepository.findById(dto.memberId())
                .orElseThrow(() -> new MemberException(ReviewErrorCode.MEMBER_NOT_FOUND));

        // 별점 유효성 검증
        if (dto.score() < 0 || dto.score() > 5) {
            throw new ReviewException(ReviewErrorCode.INVALID_RATING);
        }

        // DTO -> 엔티티 변환
        Review review = ReviewConverter.toReview(dto);
        review.setMember(member);
        review.setStore(store);

        // 사진 리스트 변환 및 연관관계 설정
        List<ReviewPhoto> photos = ReviewConverter.toReviewPhotoList(dto.photoUrls());
        photos.forEach(photo -> photo.setReview(review));
        review.getReviewPhotoList().addAll(photos);

        // 저장 및 결과 반환
        Review saved = reviewRepository.save(review);

        // 엔티티 -> 응답 DTO 변환
        return ReviewConverter.toCreateReviewResult(saved);
    }

    // 특정 가게 리뷰 전체 조회, 특정 가게에 달린 모든 리뷰를 최신순으로 페이징 조회
    public ReviewResDTO.ReviewList getStoreReviews(Long storeId, int page, int size) {
        // 가게 존재 여부 확인
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(ReviewErrorCode.STORE_NOT_FOUND));

        // 데이터 조회
        Page<Review> reviewPage = reviewRepository.findByStoreIdOrderByCreatedAtDesc(
                store.getId(),
                PageRequest.of(page, size)
        );

        // 엔티티 리스트를 DTO 리스트로 변환
        return ReviewConverter.toReviewListDTO(reviewPage);
    }

    // 나의 리뷰 목록 조회, 커서 기반 페이지네이션
    // 사용자가 특정 가게에 남긴 리뷰를 별점순 또는 최신순으로 조회
    public ReviewResDTO.CursorPagination<ReviewResDTO.ReviewItem> getMyReviewList(Long memberId, Long storeId, String cursor, String sortType, int size) {
        // 사용자와 가게가 존재하는지 먼저 확인
        if (!memberRepository.existsById(memberId)) throw new MemberException(ReviewErrorCode.MEMBER_NOT_FOUND);
        if (!storeRepository.existsById(storeId)) throw new StoreException(ReviewErrorCode.STORE_NOT_FOUND);

        PageRequest pageRequest = PageRequest.of(0, size);
        Slice<Review> reviewSlice;

        try {
            // 정렬 조건에 따른 데이터 조회
            if ("rating".equals(sortType)) {
                // 별점 순: "4.5:12" 형태의 커서 파싱
                Float lastRating = null;
                Long lastId = null;

                if (cursor != null && !cursor.equals("-1")) {
                    String[] parts = cursor.split(":");

                    // 커서 형식 에러 처리
                    if (parts.length != 2) throw new ReviewException(ReviewErrorCode.INVALID_CURSOR);

                    lastRating = Float.parseFloat(parts[0]);
                    lastId = Long.parseLong(parts[1]);
                }

                reviewSlice = reviewRepository.findAllByMemberIdAndStoreIdAndRatingCursorOrder(memberId, storeId, lastRating, lastId, pageRequest);
            } else {
                // 최신 순: ID 형태의 커서 파싱
                Long lastId = (cursor != null && !cursor.equals("-1")) ? Long.parseLong(cursor) : null;
                reviewSlice = reviewRepository.findAllByMemberIdAndStoreIdAndIdLessThanOrderByIdDesc(memberId, storeId, lastId, pageRequest);
            }
        } catch (Exception e) {
            // 파싱 중 발생하는 모든 에러를 INVALID_CURSOR로 통일
            throw new ReviewException(ReviewErrorCode.INVALID_CURSOR);
        }

        // 엔티티 -> DTO 변환
        List<ReviewResDTO.ReviewItem> reviewItems = ReviewConverter.toReviewItemDTOList(reviewSlice.getContent());

        // 다음 페이지를 위한 다음 커서 생성 (마지막 요소의 값 추출)
        String nextCursor = null;
        if (reviewSlice.hasNext()) {
            // 조회된 데이터 리스트의 마지막 항목 정보를 추출
            Review lastReview = reviewSlice.getContent().get(reviewSlice.getNumberOfElements() - 1);
            nextCursor = "rating".equals(sortType)
                    ? lastReview.getScore() + ":" + lastReview.getId()     // 별점순은 복합 커서
                    : String.valueOf(lastReview.getId());                   // 최신순은 ID 커서
        }

        // 컨버터를 통해 최종 응답 규격으로 반환
        return ReviewConverter.toCursorPagination(reviewItems, nextCursor, reviewSlice.hasNext());
    }
}
