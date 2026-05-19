package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.member.enums.SocialType;
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

import java.time.LocalDate;

// 회원 정보를 저장하는 JPA Entity입니다.
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "member")
public class Member extends BaseEntity {

    // 회원 기본키입니다.
    @Id
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

    // 회원 주소입니다.
    @Column(nullable = false, length = 255)
    private String address;

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

    @Column(nullable = false)
    private String password;

    // 회원 전화번호입니다.
    @Column(name = "phone_number", length = 11)
    private String phoneNumber;

    // 프로필 이미지 URL입니다.
    @Column(name = "profile_url")
    private String profileUrl;
}
