package com.example.umc10th.domain.review.entity;

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
public class Reply {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="reply_content")
    private String replyContent;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="review_id")
    private Review review;

}
