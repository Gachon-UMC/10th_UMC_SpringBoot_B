package org.example.umc10thyongjae.domain.auth.repository;

import org.example.umc10thyongjae.domain.auth.entity.FoodPreference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface FoodPreferenceRepository extends JpaRepository<FoodPreference, Long> {

    List<FoodPreference> findByLabelIn(Collection<String> labels);
}
