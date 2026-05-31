package com.example.umc10th.domain.auth.service;

import com.example.umc10th.domain.auth.converter.AuthConveter;
import com.example.umc10th.domain.auth.dto.AuthReqDto;
import com.example.umc10th.domain.auth.dto.AuthResDto;
import com.example.umc10th.domain.auth.entity.RefreshToken;
import com.example.umc10th.domain.auth.exception.AuthException;
import com.example.umc10th.domain.auth.exception.code.AuthErrorCode;
import com.example.umc10th.domain.auth.repository.RefreshTokenRepository;
import com.example.umc10th.domain.user.entity.Food;
import com.example.umc10th.domain.user.entity.Term;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.exception.UserException;
import com.example.umc10th.domain.user.exception.code.UserErrorCode;
import com.example.umc10th.domain.user.repository.*;
import com.example.umc10th.global.apiPayload.exception.ProjectException;
import com.example.umc10th.global.security.entity.AuthMember;
import com.example.umc10th.global.security.util.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserFoodRepository userfoodRepository;
    private final UserTermRepository usertermRepository;
    private final TermReposioty termRepository;
    private final FoodRepository foodRepository;
    private final JwtUtil jwtUtil;
    private final RefreshTokenRepository refreshTokenRepository;

    public AuthResDto.SignupResult signup(AuthReqDto.Signup signup) {

        validateDuplicateEmail(signup.email());

        Set<Long> requestedTermIdSet = new HashSet<>(signup.termIds());

        List<Term> terms = validateTermExist(requestedTermIdSet);

        validateRequiredTerms(signup.termIds());

        String encodedPassword = passwordEncoder.encode(signup.password());

        User user = AuthConveter.toUser(signup,encodedPassword);

        User savedUser = userRepository.save(user);

        usertermRepository.saveAll(AuthConveter.toUserTerms(savedUser,terms));

        List<Food> foods = signup.foodIds() ==null ? List.of() : foodRepository.findAllById(signup.foodIds());

        userfoodRepository.saveAll(AuthConveter.toUserFoods(savedUser,foods));

        return AuthConveter.toSignup(savedUser);
    }

    public AuthResDto.LoginResult login(AuthReqDto.LoginResult result) {
        User user = userRepository.findByEmail(result.Email())
                .orElseThrow(()->new UserException(UserErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(result.password(), user.getPassword())) {
            throw new AuthException(AuthErrorCode.INVALID_PASSWORD);
        }

        AuthMember authMember = new AuthMember(user);

        String accessToken = jwtUtil.createAccessToken(authMember);
        String refreshToken = jwtUtil.createRefreshToken(authMember);

        RefreshToken savedRefreshToken = refreshTokenRepository.findByUserId(user.getId()).orElse(null);

        if(savedRefreshToken == null){
            refreshTokenRepository.save(
                    RefreshToken.builder()
                            .userId(user.getId())
                            .refreshToken(refreshToken)
                            .expiresAt(jwtUtil.getRefreshTokenExpiresAt())
                            .build()
            );
        }else{
            savedRefreshToken.updateToken(
                    refreshToken,jwtUtil.getRefreshTokenExpiresAt()
            );
        }

        return AuthConveter.toLogin(
                user.getId(),
                accessToken,
                refreshToken
        );
    }

    //필수 약관 검증
    private void validateRequiredTerms(List<Long> requestedTermIds) {

        List<Term> requiredTerms = termRepository.findAllByRequiredTrue();

        List<Long> requiredTermIds = requiredTerms.stream()
                .map(Term::getId)
                .toList();

        boolean agreedAllRequiredTerms = requestedTermIds.containsAll(requiredTermIds);

        if (!agreedAllRequiredTerms) {
            throw new ProjectException(AuthErrorCode.REQUIRED_TERMS_AGREED);
        }
    }

    //약관 ID 존재 여부 검증
    private List<Term> validateTermExist(Set<Long> requestedTermIds) {
        List<Term> terms = termRepository.findAllById(requestedTermIds);

        if (terms.size() != requestedTermIds.size()) {
            throw new ProjectException(AuthErrorCode.INVALID_TERM_ID);
        }
        return terms;
    }

    //이메일 중복 확인 검증
    private void validateDuplicateEmail(String email){
        if(userRepository.existsByEmail(email)){
            throw new ProjectException(UserErrorCode.USER_EMAIL_ALREADY_EXISTS);
        }
    }
}
