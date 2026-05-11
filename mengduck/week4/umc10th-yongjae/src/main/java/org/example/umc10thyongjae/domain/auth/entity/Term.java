package org.example.umc10thyongjae.domain.auth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.umc10thyongjae.domain.auth.enums.TermName;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "term")
public class Term {
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "term_id")
    private Long termId;

    @Column(name = "term_name")
    @Enumerated(EnumType.STRING)
    private TermName termName;
}
