package org.example.umc10thyongjae.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.example.umc10thyongjae.domain.auth.dto.response.UserInfoResponseDto;
import org.example.umc10thyongjae.domain.auth.entity.User;
import org.example.umc10thyongjae.domain.auth.repository.UserRepository;
import org.example.umc10thyongjae.global.apiPayload.exception.NotDataFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository authRepository;

    public UserInfoResponseDto getUserInfo(long userId) {
        User entity = authRepository.findById(userId).orElseThrow(
                () -> new NotDataFoundException("UserInfo"));

        return convertUserInfo(entity);
    }

    private static UserInfoResponseDto convertUserInfo(User user) {
        return UserInfoResponseDto.builder()
                .name(user.getName())
                .mail(user.getMail())
                .phoneNumber(user.getPhoneNumber())
                .phoneNumberVerified("Y".equals(user.getPhoneNumberVerified()))
                .point(user.getPoint())
                .build();
    }
}
