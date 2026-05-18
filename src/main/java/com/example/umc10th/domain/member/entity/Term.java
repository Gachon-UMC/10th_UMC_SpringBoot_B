package com.example.umc10th.domain.member.entity;

<<<<<<< Updated upstream
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

// 해당 클래스를 JPA Entity로 등록합니다.
@Entity
// 필드 조회를 위한 Getter를 생성합니다.
@Getter
// Entity 생성 시 builder 패턴을 사용할 수 있게 합니다.
@Builder
// JPA 기본 생성자를 protected 범위로 생성합니다.
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// 모든 필드를 받는 생성자를 생성합니다.
@AllArgsConstructor
// 매핑할 테이블 이름을 지정합니다.
@Table(name = "term")
public class Term extends BaseEntity {

    // 기본키 컬럼입니다.
    @Id
    // 기본키 값을 DB의 identity 전략으로 생성합니다.
=======
import com.example.umc10th.domain.member.enums.TermType;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 약관 정보를 저장하는 JPA Entity입니다.
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "term")
public class Term extends BaseEntity {

    // 약관 기본키입니다.
    @Id
>>>>>>> Stashed changes
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "term_id")
    private Long id;

    // 약관 종류입니다.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
<<<<<<< Updated upstream
    private com.example.umc10th.domain.member.enums.Term name;
=======
    private TermType name;
>>>>>>> Stashed changes
}
