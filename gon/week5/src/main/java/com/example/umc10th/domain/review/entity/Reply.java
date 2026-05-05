package com.example.umc10th.domain.review.entity;

import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="reply")
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="reply_id")
    private Long id;

    @Column(name="reply_content",nullable=false)
    private String replyContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="review_id",nullable=false)
    private Review review;

}
