package org.example.umc10thyongjae.domain.auth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.umc10thyongjae.global.entity.BaseEntity;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_food_preference")
public class UserFoodPreference extends BaseEntity {
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_food_preference_id")
    private Long userFoodPreferenceId;

    @Column(name = "user_id")
    private Long userId;

    @OneToOne
    @JoinColumn(name = "food_preference_id")
    private FoodPreference foodPreference;
}
