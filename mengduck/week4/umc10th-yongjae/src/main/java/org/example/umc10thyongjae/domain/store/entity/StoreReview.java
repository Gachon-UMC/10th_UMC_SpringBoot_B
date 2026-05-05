package org.example.umc10thyongjae.domain.store.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.umc10thyongjae.global.entity.BaseEntity;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "store_review")
public class StoreReview extends BaseEntity {
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "store_review_id")
    private Long storeReviewId;

    @Column(name = "user_id")
    private Long userId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(name = "score")
    private String score;

    @Column(name = "content")
    private String content;
}
