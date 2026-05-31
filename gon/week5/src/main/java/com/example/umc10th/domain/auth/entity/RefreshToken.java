package com.example.umc10th.domain.auth.entity;

import com.example.umc10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name="refresh_token")
public class RefreshToken extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="refresh_token_id")
    private Long id;

    @Column(name="user_id")
    private Long userId;

    @Column(name="refresh_token")
    private String refreshToken;

    @Column(name="expires_at", nullable = false)
    private LocalDateTime expiresAt;

    @Builder
    public RefreshToken(Long userId, String refreshToken, LocalDateTime expiresAt) {
        this.userId = userId;
        this.refreshToken = refreshToken;
        this.expiresAt = expiresAt;
    }

    public void updateToken(String refreshToken, LocalDateTime expiresAt) {

        this.refreshToken = refreshToken;
        this.expiresAt = expiresAt;
    }
}
