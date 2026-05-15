package com.example.umc10th.domain.member_mission.entity;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.member_mission.enums.MissionStatus;
import com.example.umc10th.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member_mission")
public class MemberMission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "verification_code", length = 20, unique = true)
    private String verificationCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @Enumerated(EnumType.STRING)
    @Column(name = "mission_status", nullable = false)
    @Builder.Default
    private MissionStatus missionStatus = MissionStatus.BASIC;

    public String generateVerificationCode() {
        String code = String.valueOf((int)(Math.random() * 89999999) + 10000000);
        this.verificationCode = code;
        return code;
    }
}
