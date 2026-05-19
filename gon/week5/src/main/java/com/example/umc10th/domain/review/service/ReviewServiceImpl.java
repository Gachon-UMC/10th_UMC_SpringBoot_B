package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.exception.ReviewException;
import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.exception.StoreException;
import com.example.umc10th.domain.store.exception.code.StoreErrorCode;
import com.example.umc10th.domain.store.repository.StoreRepository;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.exception.UserException;
import com.example.umc10th.domain.user.exception.code.UserErrorCode;
import com.example.umc10th.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public ReviewResDTO.CreateReview createReview(
            Long storeId,
            Long userId,
            ReviewReqDTO.CreateReview request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND.getCode()));

        Review review = Review.builder()
                .user(user)
                .store(store)
                .reviewComment(request.reviewComment())
                .star(request.star())
                .build();

        Review savedReview = reviewRepository.save(review);

        return ReviewResDTO.CreateReview.builder()
                .reviewId(savedReview.getId())
                .userId(savedReview.getUser().getId())
                .storeId(savedReview.getStore().getId())
                .reviewComment(savedReview.getReviewComment())
                .star(savedReview.getStar())
                .build();
        //원래 retrun에는 컨버터를 쓰려했는데 컨버터 없이 서비스에 넣어보고 뭐가 나한테 더 맞는지 해보고 싶어서
        //이렇게도 해봤어
    }

    //작성한 리뷰 조회
    @Override
    @Transactional
    public ReviewResDTO.CursorPagination<ReviewResDTO.GetReview> getReviews(
            Long userId, int size, String cursor, String query
    ){
        PageRequest pageRequest = PageRequest.of(0, size);

        Slice<Review> reviewList;
        String nextCursor;

        if (cursor != null && !cursor.equals("-1")) {

            String[] cursorSplit = cursor.split(":");
            switch (query.toLowerCase()) {
                case "id":

                    Long prevCursor = Long.parseLong(cursorSplit[0]);
                    Long idCursor = Long.parseLong(cursorSplit[1]);

                    reviewList = reviewRepository.findReviewByuserIdAndLessThenOrderByIdDesc(
                            userId,
                            idCursor,
                            pageRequest
                    );
                    break;
                default:
                    throw new ReviewException(ReviewErrorCode.REVIEW_NOT_FOUND);
            }
        }else {
            reviewList = reviewRepository.findReviewByUserIdOrderByIdDesc(userId, pageRequest);
        }
        nextCursor = reviewList.getContent().getLast().getId()+":"+reviewList.getContent().getFirst().getId();

        return ReviewConverter.toCursorPagination(
                reviewList.map(ReviewConverter::toGetReview).toList(),
                reviewList.hasNext(),
                nextCursor,
                reviewList.getSize()
        );
    }
}
