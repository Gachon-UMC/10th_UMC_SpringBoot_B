package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

// 리뷰 답글 Entity의 DB 접근을 담당하는 Repository입니다.
public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
