package com.example.umc10th.domain.store.entity;

import com.example.umc10th.domain.mission.entity.Region;
import com.example.umc10th.domain.user.entity.Food;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="store_name",nullable=false)
    private String storeName;

    @Column(name="store_address",nullable=false)
    private String storeAddress;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="region_id",nullable=false)
    private Region region;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="food_category_id",nullable=false)
    private Food food;
}
