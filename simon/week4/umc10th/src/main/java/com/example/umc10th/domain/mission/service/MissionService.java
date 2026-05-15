package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.member_mission.entity.MemberMission;
import com.example.umc10th.domain.member_mission.enums.MissionStatus;
import com.example.umc10th.domain.member_mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.repository.MissionRepository;

import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.enums.RegionName;
import com.example.umc10th.domain.store.exception.StoreException;
import com.example.umc10th.domain.store.exception.code.StoreErrorCode;
import com.example.umc10th.domain.store.repository.StoreRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    /**
     * 미션 도전하기
     */
    @Transactional
    public MissionResDTO.ChallengeMission challengeMission(Long missionId, MissionReqDTO.ChallengeMission dto) {
        // 존재하는 미션인지 확인
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        // 존재하는 사용자인지 확인
        Member member = memberRepository.findById(dto.memberId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 이미 도전 중인 미션인지 중복 체크
        if (memberMissionRepository.existsByMemberAndMissionAndMissionStatus(member, mission, MissionStatus.ONGOING)) {
            throw new MissionException(MissionErrorCode.MISSION_ALREADY_CHALLENGING);
        }

        // MemberMission 데이터 생성 (도전 시작)
        MemberMission newMemberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .missionStatus(MissionStatus.ONGOING)
                .build();

        memberMissionRepository.save(newMemberMission);

        // 응답 DTO 반환
        return MissionResDTO.ChallengeMission.builder()
                .missionId(mission.getId())
                .message("미션 도전을 시작했습니다.")
                .build();
    }

    /**
     * 미션 성공 누르기
     */
    @Transactional
    public MissionResDTO.CompleteMission completeMission(Long memberMissionId) {
        // 도전 중인 미션 기록 찾기
        MemberMission memberMission = memberMissionRepository.findById(memberMissionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        // 이미 완료된 미션인지 체크
        if (memberMission.getMissionStatus() == MissionStatus.DONE) {
            throw new MissionException(MissionErrorCode.MISSION_ALREADY_COMPLETED);
        }

        // 8자리 인증 번호 생성 및 저장
        String verificationCode = memberMission.generateVerificationCode();

        // 결과 반환
        return MissionResDTO.CompleteMission.builder()
                .memberMissionId(memberMission.getId())
                .missionStatus(memberMission.getMissionStatus())
                .verificationCode(verificationCode)
                .build();
    }

    /**
     * 미션 성공 인증 (사장님 확인 후 최종 완료 및 포인트 지급)
     */
    @Transactional
    public MissionResDTO.VerifyMission verifyMission(Long memberMissionId, MissionReqDTO.VerifyMission dto) {
        // 도전 기록 조회
        MemberMission memberMission = memberMissionRepository.findById(memberMissionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        // 상태 검증 (이미 완료된 미션이거나 도전 중이지 않은 경우 예외 처리)
        if (memberMission.getMissionStatus() != MissionStatus.ONGOING) {
            throw new MissionException(MissionErrorCode.MISSION_NOT_CHALLENGING);
        }

        // 최종 완료 처리
        memberMission.setMissionStatus(MissionStatus.DONE);

        // 포인트 지급
        Integer rewardPoint = memberMission.getMission().getPoint();
        memberMission.getMember().addPoint(rewardPoint);

        return MissionResDTO.VerifyMission.builder()
                .proofId(memberMission.getId())
                .message("인증이 완료되어 " + rewardPoint + "포인트가 적립되었습니다.")
                .createdAt(LocalDateTime.now().toString())
                .build();
    }

    /**
     * 홈 화면 쿼리 (현재 선택된 지역에서 도전 가능한 미션 목록 조회)
     */
    public MissionResDTO.HomeMissionResponse homeMissionList(
            Long memberId,
            RegionName regionName,
            int pageNumber,
            int pageSize,
            String sort
    ) {
        // 상단: 내가 지금까지 '완료(DONE)'한 미션 총 개수
        long completedCount = memberMissionRepository.countByMemberIdAndMissionStatus(memberId, MissionStatus.DONE);

        // 하단: 내 동네에서 도전 가능한 미션들만 페이징 조회
        Sort sortInfo = (sort != null) ? Sort.by(sort) : Sort.by("createdAt").descending();
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize, sortInfo);

        Page<MissionResDTO.HomeMissionItem> missions =
                missionRepository.findHomeMissionsByRegionAndMember(memberId, regionName, pageRequest);

        // 통합 DTO 반환
        return MissionResDTO.HomeMissionResponse.builder()
                .missionData(MissionConverter.toPagination(
                        missions,             // 1. Page 객체 통째로
                        missions.getContent() // 2. 그 안의 데이터 리스트
                ))
                .completedMissionCount(completedCount)
                .missionGoal(10) // 상시 목표 10개
                .build();
    }

    /**
     * 미션 생성하기
     */
    @Transactional
    public Void createMission(
            Long storeId,
            MissionReqDTO.CreateMission dto
            ) {
        // 가게 찾기
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        // 미션 생성
        Mission mission = MissionConverter.toMission(store, dto);

        // 미션 DB 저장
        missionRepository.save(mission);
        return null;
    }

    /**
     * 가게 내 미션들 조회
     */
    public MissionResDTO.Pagination<MissionResDTO.GetMission> getMissions(
            Long storeId,
            Integer pageSize,
            Integer pageNumber,
            String sort
    ) {
        // 정렬 정보 생성
        Sort sortInfo = (sort != null) ? Sort.by(sort) : Sort.by("id").descending();

        // 페이지 정보들을 PageRequest로 만들기
        // 사용자의 1페이지 = 서버의 0페이지
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize, sortInfo);

        // 가게 내 미션들 조회
        Page<Mission> missionList = missionRepository.findAllByStoreId(storeId, pageRequest);

        // 미션들 응답 DTO로 포장하기
        return MissionConverter.toPagination(
                missionList,                                                // 1. Page 객체 통째로
                missionList.map(MissionConverter::toGetMission).toList()    // 2. DTO로 변환된 리스트
        );
    }
}
