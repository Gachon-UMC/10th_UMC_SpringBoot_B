package org.example.umc10thyongjae.domain.store.service;

import lombok.RequiredArgsConstructor;
import org.example.umc10thyongjae.domain.store.dto.response.StoreReviewResponseDto;
import org.example.umc10thyongjae.domain.store.entity.StoreReview;
import org.example.umc10thyongjae.domain.store.enums.StoreReviewSortType;
import org.example.umc10thyongjae.domain.store.repository.StoreReviewRepository;
import org.example.umc10thyongjae.global.dto.CursorPaginationDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreReviewService {

    private final StoreReviewRepository storeReviewRepository;

    public CursorPaginationDto<StoreReviewResponseDto> getMyReviewList(
            Long userId,
            Long cursor,
            Integer size,
            StoreReviewSortType sort
    ) {

        Pageable pageable = PageRequest.of(
                0,
                size);

        Slice<StoreReview> reviewList = switch (sort) {

            case LATEST ->
                    storeReviewRepository.findMyReviewListLatest(
                            userId,
                            cursor,
                            pageable
                    );

            case OLDEST ->
                    storeReviewRepository.findMyReviewListOldest(
                            userId,
                            cursor,
                            pageable
                    );

            case ID ->
                    storeReviewRepository.findMyReviewListById(
                            userId,
                            cursor,
                            pageable
                    );

            case SCORE ->
                    storeReviewRepository.findMyReviewListByScore(
                            userId,
                            cursor,
                            pageable
                    );
        };

        boolean hasNext = reviewList.hasNext();

        List<StoreReview> reviewContent = reviewList.getContent();

        Long nextCursor = null;

        if (hasNext && !reviewContent.isEmpty()) {
            nextCursor =
                    reviewContent.get(reviewContent.size() - 1).getId();
        }

        List<StoreReviewResponseDto> result = reviewContent.stream()
                .map(StoreReviewService::convertReviewDto)
                .toList();

        return CursorPaginationDto.<StoreReviewResponseDto>builder()
                .nextCursor(
                        nextCursor == null
                                ? null
                                : nextCursor.toString()
                )
                .size(size)
                .hasNext(hasNext)
                .data(result)
                .build();
    }

    private static StoreReviewResponseDto convertReviewDto(
            StoreReview review
    ) {
        return StoreReviewResponseDto.builder()
                .reviewId(review.getId())
                .userId(review.getUser().getId())
                .userName(review.getUser().getName())
                .storeId(review.getStore().getId())
                .storeName(review.getStore().getName())
                .score(review.getScore())
                .content(review.getContent())
                .createdAt(review.getCreatedAt().toString())
                .build();
    }
}