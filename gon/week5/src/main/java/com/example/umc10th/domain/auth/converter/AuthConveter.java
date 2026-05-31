package com.example.umc10th.domain.auth.converter;

import com.example.umc10th.domain.auth.dto.AuthReqDto;
import com.example.umc10th.domain.auth.dto.AuthResDto;
import com.example.umc10th.domain.user.entity.Food;
import com.example.umc10th.domain.user.entity.Term;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.entity.mapping.UserFood;
import com.example.umc10th.domain.user.entity.mapping.UserTerm;
import com.example.umc10th.domain.user.enums.SocialType;


import java.util.List;

public class AuthConveter {

    public static User toUser(AuthReqDto.Signup signup, String encodedPassword){
        return User.builder()
                .email(signup.email())
                .password(encodedPassword)
                .name(signup.name())
                .gender(signup.gender())
                .birth(signup.birth())
                .address(signup.address())
                .point(signup.point())
                .detailAddress(signup.detailAddress())
                .phoneNumber(signup.phoneNumber())
                .nickname(signup.nickName())
                .socialType(SocialType.LOCAL)
                .build();
    }

    public static AuthResDto.SignupResult toSignup(User user){
        return new AuthResDto.SignupResult(
                user.getId(),
                user.getEmail(),
                user.getName()
        );
    }

    public static List<UserTerm> toUserTerms(User user,List<Term> terms){
        return terms.stream()
                .map(term -> UserTerm.builder()
                        .user(user)
                        .term(term)
                        .termName(term.getTermName())
                        .agreed(true)
                        .build())
                .toList();
    }

    public static List<UserFood> toUserFoods(User user,List<Food> foods){
        return foods.stream()
                .map(food -> UserFood.builder()
                        .user(user)
                        .food(food)
                        .build())
                .toList();
    }

    public static AuthResDto.LoginResult toLogin(Long userId,String accessToken,String refreshToken){
        return new AuthResDto.LoginResult(userId, accessToken,refreshToken);
    }
}
