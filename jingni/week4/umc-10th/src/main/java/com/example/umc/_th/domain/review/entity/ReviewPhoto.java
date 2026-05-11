package com.example.umc._th.domain.review.entity;

import com.example.umc._th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "review_photo")
public class ReviewPhoto extends BaseEntity {

    @Id
    @Column(name = "id", length = 255)
    private String id;

    @Column(name = "photo_url", columnDefinition = "TEXT")
    private String photoUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;
}