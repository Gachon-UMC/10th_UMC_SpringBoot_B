package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.member_mission.entity.MemberMission;
import com.example.umc10th.domain.member_mission.enums.MissionStatus;
import com.example.umc10th.domain.member_mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.enums.RegionName;
import com.example.umc10th.domain.store.exception.StoreException;
import com.example.umc10th.domain.store.exception.code.StoreErrorCode;
import com.example.umc10th.domain.store.repository.StoreRepository;
import com.example.umc10th.global.apiPayload.dto.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    /**
     * 특정 가게에 등록된 모든 미션 목록을 페이징하여 조회
     */
    public PageResponse<MissionResDTO.GetMission> getMissions(Long storeId, Pageable pageable) {
        // 1-based index 보정
        Pageable adjustedPageable = PageRequest.of(
                Math.max(0, pageable.getPageNumber() - 1),
                pageable.getPageSize(),
                pageable.getSort()
        );

        Page<Mission> missionList = missionRepository.findAllByStoreId(storeId, adjustedPageable);
        return MissionConverter.toGetMissionPage(missionList);
    }

    /**
     * 사용자의 홈 화면에 표시될 커스텀 미션 데이터를 조회
     * 사용자가 현재 위치한 지역에서 도전 가능한 미션들과 누적 완료 횟수를 반환
     */
    public MissionResDTO.HomeMissionResponse homeMissionList(Long memberId, RegionName regionName, Pageable pageable) {
        long completedCount = memberMissionRepository.countByMemberIdAndMissionStatus(memberId, MissionStatus.DONE);

        // 1-based index 보정
        Pageable adjustedPageable = PageRequest.of(
                Math.max(0, pageable.getPageNumber() - 1),
                pageable.getPageSize(),
                pageable.getSort()
        );

        Page<Mission> missions = missionRepository.findHomeMissionsByRegionAndMember(memberId, regionName, adjustedPageable);
        List<MissionResDTO.HomeMissionItem> missionItems = missions.map(MissionConverter::toHomeMissionItem).toList();

        return MissionResDTO.HomeMissionResponse.builder()
                .missionData(MissionConverter.toPageResponse(missions, missionItems))
                .completedMissionCount(completedCount)
                .missionGoal(10)
                .build();
    }

    /**
     * 사장님이 특정 가게에 새로운 미션을 등록
     */
    @Transactional
    public Void createMission(Long storeId, MissionReqDTO.CreateMission dto) {
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
     * 사용자가 특정 미션에 대해 도전을 시작
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
     * 미션 성공 누르기 (사용자: 사장님께 보여줄 인증번호 발급)
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
     * 미션 성공 인증 (사장님: 번호 확인 후 최종 완료)
     */
    @Transactional
    public MissionResDTO.VerifyMission verifyMission(Long memberMissionId, MissionReqDTO.VerifyMission dto) {
        // 도전 기록 조회
        MemberMission memberMission = memberMissionRepository.findById(memberMissionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        // 진행 중인 미션인지 상태 검증 (이미 완료된 미션이거나 도전 중이지 않은 경우 예외 처리)
        if (memberMission.getMissionStatus() != MissionStatus.ONGOING) {
            throw new MissionException(MissionErrorCode.MISSION_NOT_CHALLENGING);
        }

        // 사용자의 인증번호와 사장님이 입력한 번호가 일치하는지 확인
        if (!memberMission.getVerificationCode().equals(dto.verificationCode())) {
            throw new MissionException(MissionErrorCode.INVALID_VERIFICATION_CODE);
        }

        // 최종 완료 처리
        memberMission.complete();

        return MissionResDTO.VerifyMission.builder()
                .proofId(memberMission.getId())
                .message("인증이 완료되어 " + memberMission.getMission().getPoint() + "포인트가 적립되었습니다.")
                .createdAt(memberMission.getCreatedAt().toString())
                .build();
    }
}
