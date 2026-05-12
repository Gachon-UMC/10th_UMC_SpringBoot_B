package com.example.umc10th.domain.food.entity;

import com.example.umc10th.domain.food.enums.FoodCategoryName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "food_category_name", nullable = false)
    @Enumerated(EnumType.STRING)
    private FoodCategoryName foodCategoryName;
}
