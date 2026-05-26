package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.HomeResDTO;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Gender;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.global.security.AuthMember;
import com.example.umc10th.global.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public MemberResDTO.SignupResponse signup(MemberReqDTO.SignupRequest request) {
        if (memberRepository.existsByEmail(request.email())) {
            throw new MemberException(MemberErrorCode.EMAIL_ALREADY_EXISTS);
        }

        Gender gender;
        try {
            gender = Gender.valueOf(request.gender().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new MemberException(MemberErrorCode.INVALID_GENDER);
        }

        String encodedPassword = passwordEncoder.encode(request.password());
        Member saved = memberRepository.save(MemberConverter.toMember(request, encodedPassword, gender));

        return MemberConverter.toSignupResponse(saved);
    }

    @Transactional(readOnly = true)
    public MemberResDTO.LoginResponse login(MemberReqDTO.LoginRequest request) {
        Member member = memberRepository.findByEmail(request.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        if (!passwordEncoder.matches(request.password(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.INVALID_PASSWORD);
        }

        AuthMember authMember = new AuthMember(member);
        String accessToken = jwtUtil.createAccessToken(authMember);

        return MemberResDTO.LoginResponse.builder()
                .accessToken(accessToken)
                .build();
    }

    @Transactional(readOnly = true)
    public MemberResDTO.MypageResponse getMypage(Long userId) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        return MemberConverter.toMypageResponse(member);
    }

    @Transactional(readOnly = true)
    public HomeResDTO.HomeResponse getHome(Long userId, Long regionId, int page, int size) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Page<Mission> missionPage = missionRepository.findByStoreRegionId(regionId, PageRequest.of(page, size));

        String regionName = missionPage.getContent().isEmpty()
                ? ""
                : missionPage.getContent().get(0).getStore().getRegion().getRegionName();

        return MemberConverter.toHomeResponse(member, regionName, missionPage);
    }
}
