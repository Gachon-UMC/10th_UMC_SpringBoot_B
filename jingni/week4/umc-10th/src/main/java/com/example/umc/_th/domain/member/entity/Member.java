package com.example.umc._th.domain.member.entity;

import com.example.umc._th.domain.member.enums.Gender;
import com.example.umc._th.domain.member.enums.SocialType;
import com.example.umc._th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name="birth")
    private LocalDate birth;

    @Column(name="address")
    private String address;

    @Column(name="social_type")
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Column(name="is_active")
    private Boolean isActive;

    @Column(name="point")
    private Integer point;
}
