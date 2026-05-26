package com.example.umc10th.domain.user.entity;

import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.mission.enums.Address;
import com.example.umc10th.domain.user.entity.mapping.UserFood;
import com.example.umc10th.domain.user.entity.mapping.UserTerm;
import com.example.umc10th.domain.user.enums.Gender;
import com.example.umc10th.domain.user.enums.SocialType;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    @Column(name="email",nullable=false)
    private String email;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="nickname",nullable = false)
    private String nickname;

    @Column(name="password",nullable=false)
    private String password;

    @Column(name="point",nullable=false)
    private int point;

    @Column(name="phoneNumber",nullable=false)
    private String phoneNumber;

    @Column(name="profileUrl")
    private String profileUrl;

    @Column(name="birth", nullable = false)
    private LocalDate birth;

    @Column(name="gender",nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name="social_uid")
    private String socialUid;

    @Column(name="social_type",nullable=false)
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Column(name="address",nullable=false)
    private String address;

    @Column(name="detail_address",nullable = false)
    private String detailAddress;

    @OneToMany(mappedBy="user",cascade=CascadeType.REMOVE)
    private List<UserMission> userMisisonList = new ArrayList<>();


}
