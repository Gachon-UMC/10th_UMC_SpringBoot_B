package com.example.umc10th.domain.review.entity;

import com.example.umc10th.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="review")
public class Review {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="reviewContent")
    private String reviewContent;

    @Column(name="star")
    private Integer star;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="store_id")
    private Store store;





}
