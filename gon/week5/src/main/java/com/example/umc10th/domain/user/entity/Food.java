package com.example.umc10th.domain.user.entity;

import com.example.umc10th.domain.user.enums.FoodName;
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
@Table(name="food")
public class Food {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="food_name",nullable=false)
    @Enumerated(EnumType.STRING)
    private FoodName name;
}
