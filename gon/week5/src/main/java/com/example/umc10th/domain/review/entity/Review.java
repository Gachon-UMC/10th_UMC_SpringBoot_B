package com.example.umc10th.domain.review.entity;

import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="review")
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="reviewComment",nullable=false)
    private String reviewComment;

    @Column(name="star",nullable=false)
    private Integer star;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",nullable=false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="store_id",nullable=false)
    private Store store;

    @OneToMany(mappedBy="review",cascade = CascadeType.REMOVE)
    private List<Reply> replyList = new ArrayList<>();

    @OneToMany(mappedBy = "review",cascade = CascadeType.REMOVE)
    private List<ReveiwPhoto> reveiwPhotoList = new ArrayList<>();





}
