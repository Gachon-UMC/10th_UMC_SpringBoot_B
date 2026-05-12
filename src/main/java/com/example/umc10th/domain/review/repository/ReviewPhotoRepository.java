package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.ReviewPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

// 리뷰 사진 Entity의 DB 접근을 담당하는 Repository입니다.
public interface ReviewPhotoRepository extends JpaRepository<ReviewPhoto, Long> {
}
