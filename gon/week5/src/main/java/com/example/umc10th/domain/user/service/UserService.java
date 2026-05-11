package com.example.umc10th.domain.user.service;

import com.example.umc10th.domain.user.converter.UserConverter;
import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.exception.UserException;
import com.example.umc10th.domain.user.exception.code.UserErrorCode;
import com.example.umc10th.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //마이페이지
    public UserResDTO.GetInfo getInfo(UserResDTO.GetInfo dto) {
        // DTO에서 유저 ID를 추출
        Long userId=dto.id();
        //DB에서 해당 유저 ID로 데이터 조회
        User user = userRepository.findById(userId)
                .orElseThrow(()->new UserException(UserErrorCode.USER_NOT_FOUND));
        //컨버터를 이용해서 응답 DTO 생성&리턴
        return UserConverter.toGetInfo(user);
    }

}
