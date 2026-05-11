package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "store")
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 가게 이름
    private String name;

    // 상세 주소
    private String address;

    // 사장 ID
    private Long managerNumber;

    // 상세 주소 정보 (선택)
    private String detailAddress;

    // 지역 ID
    private Long locationId;
}