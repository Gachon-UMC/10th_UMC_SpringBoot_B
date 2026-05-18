package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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
@Table(name = "mission")
public class Mission extends BaseEntity {

    // 기본키 컬럼입니다.
    @Id
    // 기본키 값을 DB의 identity 전략으로 생성합니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 미션 마감일입니다.
    @Column(nullable = false)
    private LocalDate deadline;

    // 미션 달성 조건입니다.
    @Column(nullable = false)
    private String condition;

    // 미션 보상 포인트입니다.
    @Column(nullable = false)
    private Integer point;

    // 미션이 속한 가게입니다.
    @ManyToOne(fetch = FetchType.LAZY)
    // 외래키 컬럼명을 지정합니다.
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;
}
