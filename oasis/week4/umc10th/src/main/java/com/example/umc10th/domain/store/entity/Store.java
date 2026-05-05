package com.example.umc10th.domain.store.entity;

import com.example.umc10th.domain.food_category.entity.FoodCategory;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "store")
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private FoodCategory foodCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @Column(name = "store_name", nullable = false, length = 20)
    private String storeName;

    @Column(name = "rating_sum", nullable = false, precision = 2, scale = 1)
    private BigDecimal ratingSum;

    @Column(name = "rating_count")
    private Long ratingCount;

    @Column(name = "is_enabled", nullable = false, columnDefinition = "bit(1) default true")
    private Boolean isEnabled;

    @OneToOne(mappedBy = "store", cascade = CascadeType.ALL)
    private StoreAdress storeAdress;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    @Builder.Default
    private List<StoreImage> storeImageList = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Mission> missionList = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Review> reviewList = new ArrayList<>();
}
