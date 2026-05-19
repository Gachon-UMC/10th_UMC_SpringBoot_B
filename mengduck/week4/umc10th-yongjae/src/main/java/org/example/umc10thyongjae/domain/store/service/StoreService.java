package org.example.umc10thyongjae.domain.store.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.umc10thyongjae.domain.auth.entity.User;
import org.example.umc10thyongjae.domain.auth.repository.UserRepository;
import org.example.umc10thyongjae.domain.store.dto.request.WriteReviewRequestDto;
import org.example.umc10thyongjae.domain.store.entity.Store;
import org.example.umc10thyongjae.domain.store.entity.StoreReview;
import org.example.umc10thyongjae.domain.store.repository.StoreRepository;
import org.example.umc10thyongjae.domain.store.repository.StoreReviewRepository;
import org.example.umc10thyongjae.global.apiPayload.exception.NotDataFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final StoreReviewRepository storeReviewRepository;
    private final UserRepository userRepository;

    @Transactional
    public void saveStoreReview(long userId, long storeId, WriteReviewRequestDto dto) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new NotDataFoundException("Store"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotDataFoundException("User"));

        StoreReview review = StoreReview.builder()
                .user(user)
                .store(store)
                .score(dto.score())
                .content(dto.comment())
                .build();

        storeReviewRepository.save(review);
    }
}
