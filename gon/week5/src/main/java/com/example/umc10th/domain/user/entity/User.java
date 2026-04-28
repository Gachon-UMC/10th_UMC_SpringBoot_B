package com.example.umc10th.domain.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    @Column(name="email")
    private String email;
    @Column(name="name")
    private String name;
    @Column(name="password")
    private String password;
    @Column(name="point")
    private Integer point;
    @Column(name="phoneNumber")
    private String phoneNumber;
    @Column(name="profileUrl")
    private String profileUrl;
}
