package com.example.umc10th.domain.food_category.entity;

import com.example.umc10th.domain.member.entity.FavCategory;
import com.example.umc10th.domain.store.entity.Store;
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
@Table(name = "food_category")
public class FoodCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(name = "category_name", nullable = false, length = 20)
    private String categoryName;

    @OneToMany(mappedBy = "foodCategory", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Store> storeList = new ArrayList<>();

    @OneToMany(mappedBy = "foodCategory", cascade = CascadeType.ALL)
    @Builder.Default
    private List<FavCategory> favCategoryList = new ArrayList<>();
}
