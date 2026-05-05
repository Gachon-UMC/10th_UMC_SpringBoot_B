package org.example.umc10thyongjae.domain.auth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.umc10thyongjae.global.entity.BaseEntity;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_term")
public class UserTerm extends BaseEntity {
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_term_id")
    private Long user_term_id;

    @Column(name = "user_id")
    private Long userId;

    @OneToOne
    @JoinColumn(name = "term_id")
    private Term term;
}
