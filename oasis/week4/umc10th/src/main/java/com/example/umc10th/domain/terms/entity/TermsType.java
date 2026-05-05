package com.example.umc10th.domain.terms.entity;

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
@Table(name = "terms_type")
public class TermsType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "terms_id")
    private Long id;

    @Column(name = "terms_name", nullable = false, length = 20)
    private String termsName;

    @OneToMany(mappedBy = "termsType", cascade = CascadeType.ALL)
    @Builder.Default
    private List<TermsAllowList> termsAllowList = new ArrayList<>();
}
