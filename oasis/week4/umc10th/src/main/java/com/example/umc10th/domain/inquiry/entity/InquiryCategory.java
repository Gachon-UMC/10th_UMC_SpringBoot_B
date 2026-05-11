package com.example.umc10th.domain.inquiry.entity;

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
@Table(name = "inquiry_category")
public class InquiryCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(name = "category_name", nullable = false, length = 20)
    private String categoryName;

    @OneToMany(mappedBy = "inquiryCategory", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Inquiry> inquiryList = new ArrayList<>();
}
