package com.example.umc10th.domain.store.entity;

import com.example.umc10th.domain.store.enums.RegionName;
import com.example.umc10th.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "region")
public class Region extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "region_name", nullable = false)
    @Enumerated(EnumType.STRING)
    private RegionName regionName;
}
