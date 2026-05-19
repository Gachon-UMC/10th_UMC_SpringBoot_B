package com.example.umc10th.domain.auth.service;

import com.example.umc10th.domain.auth.converter.AuthConveter;
import com.example.umc10th.domain.auth.dto.AuthReqDto;
import com.example.umc10th.domain.auth.dto.AuthResDto;
import com.example.umc10th.domain.auth.exception.code.AuthErrorCode;
import com.example.umc10th.domain.user.entity.Food;
import com.example.umc10th.domain.user.entity.Term;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.exception.code.UserErrorCode;
import com.example.umc10th.domain.user.repository.*;
import com.example.umc10th.global.apiPayload.exception.ProjectException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public AuthResDto.SignupResult signup(AuthReqDto.Signup signup) {

        validateDuplicateEmail(signup.email());
        validateRequiredTerms(signup.termIds());

        String encodedPassword = passwordEncoder.encode(signup.password());

        User user = AuthConveter.toUser(signup,encodedPassword);

        User savedUser = userRepository.save(user);

        List<Term> terms = termRepository.findAllById(signup.termIds());

        usertermRepository.saveAll(AuthConveter.toUserTerms(savedUser,terms));

        List<Food> foods = signup.foodIds() ==null ? List.of() : foodRepository.findAllById(signup.foodIds());

        userfoodRepository.saveAll(AuthConveter.toUserFoods(savedUser,foods));

        return AuthConveter.toSignup(savedUser);
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

    //이메일 중복 확인 검증
    private void validateDuplicateEmail(String email){
        if(userRepository.existsByEmail(email)){
            throw new ProjectException(UserErrorCode.USER_EMAIL_ALREADY_EXISTS);
        }
    }
}
