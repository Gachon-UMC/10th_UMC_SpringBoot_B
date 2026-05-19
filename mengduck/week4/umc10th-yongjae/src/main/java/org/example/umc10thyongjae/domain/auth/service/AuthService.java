package org.example.umc10thyongjae.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.example.umc10thyongjae.domain.auth.dto.request.SignUpRequestDto;
import org.example.umc10thyongjae.domain.auth.dto.response.UserInfoResponseDto;
import org.example.umc10thyongjae.domain.auth.entity.FoodPreference;
import org.example.umc10thyongjae.domain.auth.entity.Term;
import org.example.umc10thyongjae.domain.auth.entity.User;
import org.example.umc10thyongjae.domain.auth.entity.UserFoodPreference;
import org.example.umc10thyongjae.domain.auth.entity.UserTerm;
import org.example.umc10thyongjae.domain.auth.enums.TermName;
import org.example.umc10thyongjae.domain.auth.repository.FoodPreferenceRepository;
import org.example.umc10thyongjae.domain.auth.repository.TermRepository;
import org.example.umc10thyongjae.domain.auth.repository.UserFoodPreferenceRepository;
import org.example.umc10thyongjae.domain.auth.repository.UserRepository;
import org.example.umc10thyongjae.domain.auth.repository.UserTermRepository;
import org.example.umc10thyongjae.global.apiPayload.exception.NotDataFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository authRepository;
    private final TermRepository termRepository;
    private final UserTermRepository userTermRepository;
    private final FoodPreferenceRepository foodPreferenceRepository;
    private final UserFoodPreferenceRepository userFoodPreferenceRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long signUp(SignUpRequestDto dto) {
        if (authRepository.existsByLoginId(dto.id())) {
            throw new IllegalArgumentException("이미 가입된 아이디입니다.");
        }

        User user = authRepository.save(User.builder()
                .loginId(dto.id())
                .pwd(passwordEncoder.encode(dto.pwd()))
                .name(dto.userInfo().name())
                .gender(dto.userInfo().gender())
                .birthDate(dto.userInfo().birthDate())
                .address(dto.userInfo().address())
                .phoneNumber(dto.userInfo().phoneNumber())
                .phoneNumberVerified("N")
                .mail(dto.userInfo().mail())
                .addressDetail(dto.userInfo().addressDetail())
                .point(0)
                .deleted("N")
                .build());

        saveUserTerms(user, dto);
        saveUserFoodPreferences(user, dto);

        return user.getId();
    }

    @Transactional(readOnly = true)
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

    private void saveUserTerms(User user, SignUpRequestDto dto) {
        if (dto.terms() == null || dto.terms().isEmpty()) {
            return;
        }

        List<UserTerm> userTerms = dto.terms().stream()
                .filter(term -> Boolean.TRUE.equals(term.value()))
                .map(term -> UserTerm.builder()
                        .user(user)
                        .term(getOrCreateTerm(term.termName()))
                        .build())
                .toList();

        userTermRepository.saveAll(userTerms);
    }

    private void saveUserFoodPreferences(User user, SignUpRequestDto dto) {
        if (dto.foodCate() == null || dto.foodCate().isEmpty()) {
            return;
        }

        Map<String, FoodPreference> foodPreferenceMap = foodPreferenceRepository.findByLabelIn(dto.foodCate()).stream()
                .collect(Collectors.toMap(FoodPreference::getLabel, Function.identity(), (a, b) -> a, HashMap::new));

        List<UserFoodPreference> userFoodPreferences = dto.foodCate().stream()
                .map(label -> {
                    FoodPreference foodPreference = foodPreferenceMap.computeIfAbsent(label, this::createFoodPreference);

                    return UserFoodPreference.builder()
                            .user(user)
                            .foodPreference(foodPreference)
                            .build();
                })
                .toList();

        userFoodPreferenceRepository.saveAll(userFoodPreferences);
    }

    private Term getOrCreateTerm(TermName termName) {
        return termRepository.findByTermName(termName)
                .orElseGet(() -> termRepository.save(Term.builder()
                        .termName(termName)
                        .build()));
    }

    private FoodPreference createFoodPreference(String label) {
        return foodPreferenceRepository.save(FoodPreference.builder()
                .label(label)
                .build());
    }
}
