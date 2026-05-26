package com.example.umc10th.global.security.dto;

import com.example.umc10th.domain.user.enums.SocialType;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
public class KakaoDTO implements OAuthDTO {

    private final String id;
    private final String name;
    private final String email;

    @Override
    public SocialType getSocialType() {
        return SocialType.KAKAO;
    }

    @Override
    public String getSocialUid() {
        return id;
    }

    @Override
    public String getSocialEmail() {
        return email;
    }

    @Override
    public String getName() {
        return name;
    }
}
