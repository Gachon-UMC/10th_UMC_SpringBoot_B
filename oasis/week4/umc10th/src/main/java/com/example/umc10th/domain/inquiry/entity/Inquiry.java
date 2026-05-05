package com.example.umc10th.domain.inquiry.entity;

import com.example.umc10th.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "inquiry")
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inquiry_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private InquiryCategory inquiryCategory;

    @Column(name = "title", nullable = false, length = 20)
    private String title;

    @Column(name = "content", nullable = false, length = 255)
    private String content;

    @Column(name = "is_answered", nullable = false, columnDefinition = "bit(1) default false")
    private Boolean isAnswered;

    @OneToMany(mappedBy = "inquiry", cascade = CascadeType.ALL)
    @Builder.Default
    private List<InquiryImage> inquiryImageList = new ArrayList<>();

    @OneToMany(mappedBy = "inquiry", cascade = CascadeType.ALL)
    @Builder.Default
    private List<InquiryAnswer> inquiryAnswerList = new ArrayList<>();
}
