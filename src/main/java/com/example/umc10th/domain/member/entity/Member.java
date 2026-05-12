package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.member.enums.SocialType;
import com.example.umc10th.domain.mission.enums.Address;
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
@Table(name = "member")
public class Member extends BaseEntity {

    // 기본키 컬럼입니다.
    @Id
    // 기본키 값을 DB의 identity 전략으로 생성합니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    // 회원 이름입니다.
    @Column(nullable = false, length = 5)
    private String name;

    // 회원 성별입니다.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    // 회원 생년월일입니다.
    private LocalDate birth;

    // 회원 주소 지역입니다.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Address address;

    // 회원 상세 주소입니다.
    @Column(name = "detail_address")
    private String detailAddress;

    // 소셜 로그인 UID입니다.
    @Column(name = "social_uid", nullable = false)
    private String socialUid;

    // 소셜 로그인 제공자입니다.
    @Enumerated(EnumType.STRING)
    @Column(name = "social_type", nullable = false)
    private SocialType socialType;

    // 회원 포인트입니다.
    @Column(nullable = false)
    private Integer point;

    // 회원 이메일입니다.
    @Column(nullable = false, length = 50)
    private String email;

    // 회원 전화번호입니다.
    @Column(name = "phone_number", length = 11)
    private String phoneNumber;

    // 프로필 이미지 URL입니다.
    @Column(name = "profile_url")
    private String profileUrl;
}
