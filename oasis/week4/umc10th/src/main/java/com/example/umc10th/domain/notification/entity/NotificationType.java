package com.example.umc10th.domain.notification.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "nofication_type")
public class NotificationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nofication_id")
    private Long id;

    @Column(name = "nofication_name", nullable = false, length = 20)
    private String noficationName;

    @OneToMany(mappedBy = "notificationType", cascade = CascadeType.ALL)
    @Builder.Default
    private List<NotificationAllowList> notificationAllowList = new ArrayList<>();
}
