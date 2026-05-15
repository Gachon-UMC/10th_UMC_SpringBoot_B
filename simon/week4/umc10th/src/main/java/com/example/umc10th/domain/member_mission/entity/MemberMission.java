package com.example.umc10th.domain.member_mission.entity;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member_mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
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
    @Column(name = "mission_status", nullable = false, columnDefinition = "VARCHAR(15) DEFAULT 'BASIC'")
    @Builder.Default
    private MissionStatus missionStatus = MissionStatus.BASIC;

    public void complete() {
        if (this.missionStatus == MissionStatus.DONE) {
            throw new MissionException(MissionErrorCode.MISSION_ALREADY_COMPLETED);
        }

        // 상태 변경
        this.missionStatus = MissionStatus.DONE;

        // 해당 미션의 포인트를 회원에게 지급
        // 미션 엔티티에서 포인트를 가져와서 멤버 엔티티의 메서드를 호출
        this.member.addPoint(this.mission.getPoint());
    }

    public String generateVerificationCode() {
        String code = String.valueOf((int)(Math.random() * 89999999) + 10000000);
        this.verificationCode = code;
        return code;
    }
}
