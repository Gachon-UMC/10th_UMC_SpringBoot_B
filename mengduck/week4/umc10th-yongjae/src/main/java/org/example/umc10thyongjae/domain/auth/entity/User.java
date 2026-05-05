package org.example.umc10thyongjae.domain.auth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.umc10thyongjae.domain.auth.enums.Gender;
import org.example.umc10thyongjae.domain.auth.enums.OAuthType;
import org.example.umc10thyongjae.global.entity.BaseEntity;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User extends BaseEntity {

    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "oauth_uid")
    private String oauthUid;

    @Column(name = "oauth_type")
    @Enumerated(EnumType.STRING)
    private OAuthType oauthType;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birth_date")
    private String birthDate;

    @Column(name = "address")
    private String address;

    @Column(name = "address_detail")
    private String addressDetail;

    @Column(name = "point")
    private Integer point;

    @Column(name = "deleted_yn")
    private String deletedYn;
}
