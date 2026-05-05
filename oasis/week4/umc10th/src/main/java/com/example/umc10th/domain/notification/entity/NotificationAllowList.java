package com.example.umc10th.domain.notification.entity;

import com.example.umc10th.domain.member.entity.Member;
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
@Table(name = "nofication_allow_list")
public class NotificationAllowList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nofication_allow_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nofication_id", nullable = false)
    private NotificationType notificationType;

    @Column(name = "is_allow", nullable = false, columnDefinition = "bit(1) default false")
    private Boolean isAllow;
}
