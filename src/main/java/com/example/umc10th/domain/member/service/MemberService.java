package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.dto.HomeResponseDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public HomeResponseDTO getMyPage(Long userId) {

        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저 없음"));

        return new HomeResponseDTO(
                member.getName(),
                member.getEmail(),
                member.getPhoneNumber(),
                member.getPoint()
        );
    }
}