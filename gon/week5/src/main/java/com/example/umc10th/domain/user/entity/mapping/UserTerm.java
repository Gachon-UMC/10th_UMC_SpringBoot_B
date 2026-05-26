package com.example.umc10th.domain.user.entity.mapping;

import com.example.umc10th.domain.user.entity.Term;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.enums.TermName;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user_term")
public class UserTerm extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="user_term_id")
    private Long Id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id",nullable=false)
    private User user;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="term_id",nullable=false)
    private Term term;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TermName termName;

    @Column (nullable = false)
    private Boolean agreed;

}
