package com.example.umc10th.domain.term.entity;

import com.example.umc10th.domain.term.enums.TermName;
import com.example.umc10th.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "term")
public class Term extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "term_name", nullable = false)
    @Enumerated(EnumType.STRING)
    private TermName termName;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "optional", nullable = false)
    private Boolean optional;
}