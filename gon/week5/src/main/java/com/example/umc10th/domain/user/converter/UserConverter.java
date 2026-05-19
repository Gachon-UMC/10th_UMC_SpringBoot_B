package com.example.umc10th.domain.user.converter;

import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserConverter {

    public static UserResDTO.GetInfo toGetInfo(User user){
        return UserResDTO.GetInfo.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .point(user.getPoint())
                .phoneNumber(user.getPhoneNumber())
                .profileUrl(user.getProfileUrl())
                .build();
    }




}
