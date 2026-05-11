package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.example.umc10th.domain.store.entity.Store;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="mission")
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mission_id")
    private Long id;

    @Column(name="title",nullable = false)
    private String title;

    @Column(name="description",nullable = false)
    private String description;

    @Column(name="point",nullable = false)
    private int point;

    @Column(name="deadline",nullable = false)
    private LocalDate deadline;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="store_id",nullable = false)
    private Store store;
}
