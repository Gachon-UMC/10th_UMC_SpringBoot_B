package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.entity.mapping.MemberFood;
import org.springframework.data.jpa.repository.JpaRepository;

// 회원 선호 음식 매핑 Entity의 DB 접근을 담당하는 Repository입니다.
public interface MemberFoodRepository extends JpaRepository<MemberFood, Long> {
}
