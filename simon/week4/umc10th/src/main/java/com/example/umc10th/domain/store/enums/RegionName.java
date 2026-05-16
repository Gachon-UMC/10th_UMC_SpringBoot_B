package com.example.umc10th.domain.store.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RegionName {
    // 서울특별시
    SEOUL_JONGNO("서울특별시 종로구"), SEOUL_JUNG("서울특별시 중구"), SEOUL_YONGSAN("서울특별시 용산구"),
    SEOUL_SEONGDONG("서울특별시 성동구"), SEOUL_GWANGJIN("서울특별시 광진구"), SEOUL_DONGDAEMUN("서울특별시 동대문구"),
    SEOUL_JUNGNANG("서울특별시 중랑구"), SEOUL_SEONGBUK("서울특별시 성북구"), SEOUL_GANGBUK("서울특별시 강북구"),
    SEOUL_DOBONG("서울특별시 도봉구"), SEOUL_NOWON("서울특별시 노원구"), SEOUL_EUNPYEONG("서울특별시 은평구"),
    SEOUL_SEODAEMUN("서울특별시 서대문구"), SEOUL_MAPO("서울특별시 마포구"), SEOUL_YANGCHEON("서울특별시 양천구"),
    SEOUL_GANGSEO("서울특별시 강서구"), SEOUL_GURO("서울특별시 구로구"), SEOUL_GEUMCHEON("서울특별시 금천구"),
    SEOUL_YEONGDEUNGPO("서울특별시 영등포구"), SEOUL_DONGJAK("서울특별시 동작구"), SEOUL_GWANAK("서울특별시 관악구"),
    SEOUL_SEOCHO("서울특별시 서초구"), SEOUL_GANGNAM("서울특별시 강남구"), SEOUL_SONGPA("서울특별시 송파구"), SEOUL_GANGDONG("서울특별시 강동구"),

    // 경기도
    GYEONGGI_SEONGNAM_SUJEONG("경기도 성남시 수정구"),
    GYEONGGI_SEONGNAM_JUNGWON("경기도 성남시 중원구"),
    GYEONGGI_SEONGNAM_BUNDANG("경기도 성남시 분당구"),
    GYEONGGI_SUWON_PALDAL("경기도 수원시 팔달구"),
    GYEONGGI_YONGIN_SUJI("경기도 용인시 수지구"),

    // 부산광역시
    BUSAN_JUNG("부산광역시 중구"), BUSAN_SEO("부산광역시 서구"), BUSAN_DONG("부산광역시 동구"),
    BUSAN_YEONGDO("부산광역시 영도구"), BUSAN_BUSANJIN("부산광역시 부산진구"), BUSAN_DONGNAE("부산광역시 동래구"),
    BUSAN_NAM("부산광역시 남구"), BUSAN_BUK("부산광역시 북구"), BUSAN_HAEUNDAE("부산광역시 해운대구"),
    BUSAN_SAHA("부산광역시 사하구"), BUSAN_GEUMJEONG("부산광역시 금정구"), BUSAN_GANGSEO("부산광역시 강서구"),
    BUSAN_YEONJE("부산광역시 연제구"), BUSAN_SUYEONG("부산광역시 수영구"), BUSAN_SASANG("부산광역시 사상구"), BUSAN_GIJANG("부산광역시 기장군"),

    // 인천광역시
    INCHEON_JUNG("인천광역시 중구"), INCHEON_DONG("인천광역시 동구"), INCHEON_MICHUHOL("인천광역시 미추홀구"),
    INCHEON_YEONSU("인천광역시 연수구"), INCHEON_NAMDONG("인천광역시 남동구"), INCHEON_BUPYEONG("인천광역시 부평구"),
    INCHEON_GYEYANG("인천광역시 계양구"), INCHEON_SEO("인천광역시 서구"), INCHEON_GANGHWA("인천광역시 강화군"), INCHEON_ONGJIN("인천광역시 옹진군"),

    // 대구광역시
    DAEGU_JUNG("대구광역시 중구"), DAEGU_DONG("대구광역시 동구"), DAEGU_SEO("대구광역시 서구"),
    DAEGU_NAM("대구광역시 남구"), DAEGU_BUK("대구광역시 북구"), DAEGU_SUSEONG("대구광역시 수성구"),
    DAEGU_DALSEO("대구광역시 달서구"), DAEGU_DALSEONG("대구광역시 달성군"), DAEGU_GUNWI("대구광역시 군위군"),

    // 대전광역시
    DAEJEON_DONG("대전광역시 동구"), DAEJEON_JUNG("대전광역시 중구"), DAEJEON_SEO("대전광역시 서구"),
    DAEJEON_YUSEONG("대전광역시 유성구"), DAEJEON_DAEDEOK("대전광역시 대덕구"),

    // 광주광역시
    GWANGJU_DONG("광주광역시 동구"), GWANGJU_SEO("광주광역시 서구"), GWANGJU_NAM("광주광역시 남구"),
    GWANGJU_BUK("광주광역시 북구"), GWANGJU_GWANSAN("광주광역시 광산구"),

    // 울산광역시
    ULSAN_JUNG("울산광역시 중구"), ULSAN_NAM("울산광역시 남구"), ULSAN_DONG("울산광역시 동구"),
    ULSAN_BUK("울산광역시 북구"), ULSAN_ULJU("울산광역시 울주군");

    private final String description;
}
