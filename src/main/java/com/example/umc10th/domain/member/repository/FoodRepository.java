package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

// 음식 Entity의 DB 접근을 담당하는 Repository입니다.
public interface FoodRepository extends JpaRepository<Food, Long> {
}
