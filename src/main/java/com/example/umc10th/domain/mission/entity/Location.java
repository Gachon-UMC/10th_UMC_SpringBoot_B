package com.example.umc10th.domain.mission.entity;

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
@Table(name = "location")
public class Location extends BaseEntity {

    // 기본키 컬럼입니다.
    @Id
    // 기본키 값을 DB의 identity 전략으로 생성합니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 지역 이름입니다.
    @Column(nullable = false)
    private String name;
}
