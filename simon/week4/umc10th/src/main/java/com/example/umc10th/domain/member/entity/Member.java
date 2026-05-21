package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.member.entity.mapping.MemberPreferFood;
import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.member.enums.MemberStatus;
import com.example.umc10th.domain.member.enums.SocialType;
import com.example.umc10th.domain.member_mission.entity.MemberMission;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false, length = 128)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "social_uid")
    private String socialUid;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_type", nullable = false)
    private SocialType socialType;

    @Column(name = "point", nullable = false)
    @Builder.Default
    private Integer point = 0;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "detail_address")
    private String detailAddress;

    @Column(name = "profile_url")
    private String profileUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_status", nullable = false)
    @Builder.Default
    private MemberStatus memberStatus = MemberStatus.ACTIVE;

    public void updateProfile(String phoneNumber, String profileUrl) {
        if (phoneNumber != null) this.phoneNumber = phoneNumber;
        if (profileUrl != null) this.profileUrl = profileUrl;
    }

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @Builder.Default
    private List<MemberMission> memberMissionList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @Builder.Default
    private List<MemberPreferFood> memberPreferFoodList = new ArrayList<>();

    public void addPoint(Integer point) {
        if (this.point == null) this.point = 0;
        this.point += point;
    }

    public void encodePassword(String encodedPassword) {
        this.password = encodedPassword;
    }

    public void withdraw() {
        this.memberStatus = MemberStatus.INACTIVE;
    }
}
