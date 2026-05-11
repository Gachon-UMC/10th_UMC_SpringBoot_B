package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.domain.mission.enums.RegionName;
import com.example.umc10th.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="region")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="region_id",nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name="region_name",nullable = false)
    private RegionName regionName;


    @OneToMany(mappedBy = "region",cascade = CascadeType.REMOVE)
    private List<Store>  storeList = new ArrayList<>();
}
