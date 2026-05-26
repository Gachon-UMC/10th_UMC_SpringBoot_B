package com.example.umc10th.domain.user.entity;

import com.example.umc10th.domain.user.enums.TermName;
import com.example.umc10th.global.entity.BaseEntity;
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
@Table(name="term")
public class Term extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="term_name",nullable=false)
    @Enumerated(EnumType.STRING)
    private TermName termName;

    @Column(name="required",nullable = false)
    private boolean required;

}
