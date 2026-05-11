package com.example.umc10th.domain.store.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="store_name")
    private String storeName;

    @Column(name="store_address")
    private String storeAddress;

    @Column(name="region_id")
    private Long regionId;
}
