package com.example.umc10th.domain.user.dto;

import com.example.umc10th.domain.user.entity.Term;
import com.example.umc10th.domain.user.enums.FoodName;
import com.example.umc10th.domain.user.enums.Gender;
import com.example.umc10th.domain.user.enums.TermName;

import java.time.LocalDate;

public class UserReqDTO {

    public record GetInfo(
            Long id
    ){}

}
