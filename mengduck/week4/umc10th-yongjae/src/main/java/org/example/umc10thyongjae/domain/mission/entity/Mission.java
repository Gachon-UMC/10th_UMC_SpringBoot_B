package org.example.umc10thyongjae.domain.mission.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.umc10thyongjae.domain.store.entity.Store;
import org.example.umc10thyongjae.global.entity.BaseEntity;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mission")
public class Mission extends BaseEntity {
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "mission_id")
    private Long missionId;

    @Column(name = "location")
    private String location;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(name = "deleted_yn")
    private String deletedYn;

    @Column(name = "reward")
    private Integer reward;

    @Column(name = "description")
    private String description;

    @Column(name = "user_id")
    private LocalDateTime expireDate;
}
