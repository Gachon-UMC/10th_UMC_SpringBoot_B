package org.example.umc10thyongjae.domain.auth.entity;

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
@Table(name = "food_preference")
public class FoodPreference {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_preference_id")
    private Long id;

    @Column(name = "label")
    private String label;
}
