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
@Table(name = "store_review_reply")
public class StoreReviewReply extends BaseEntity {
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "store_review_reply_id")
    private Long storeReviewReplyId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "store_review_id")
    private Long storeReviewId;

    @Column(name = "content")
    private String content;
}