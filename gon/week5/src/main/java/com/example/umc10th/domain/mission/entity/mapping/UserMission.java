package com.example.umc10th.domain.mission.entity.mapping;

import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.enums.UserMissionStatus;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user_mission")
public class UserMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name="status",nullable = false)
    private UserMissionStatus status;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id",nullable=false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    public void success() {
        this.status = UserMissionStatus.SUCCESS;
    }
    //서비스에서 직접 바꿀 수 있지만 세터를 열어두는건 위험하다 생각해서 엔티티 안에 넣음
    //근데 내가 한게 맞는 건지 모르겠어서 한번더 피드백 부탁해~~
}
