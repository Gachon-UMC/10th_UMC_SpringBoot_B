package com.example.umc10th.global.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// 공통 필드 조회를 위한 Getter를 생성합니다.
@Getter
// Entity가 공통 필드를 상속받을 수 있게 하는 상위 매핑 클래스입니다.
@MappedSuperclass
// 생성일/수정일 자동 기록을 위한 JPA Auditing Listener를 연결합니다.
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    // Entity 생성 시각을 자동으로 기록합니다.
    @CreatedDate
    private LocalDateTime createdAt;

    // Entity 수정 시각을 자동으로 기록합니다.
    @LastModifiedDate
    private LocalDateTime updatedAt;

    // 소프트 삭제 시각입니다.
    private LocalDateTime deletedAt;
}
