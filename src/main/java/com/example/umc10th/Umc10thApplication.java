package com.example.umc10th;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// Spring Boot 애플리케이션의 시작점으로 등록합니다.
@SpringBootApplication
// BaseEntity의 생성일/수정일 자동 기록을 활성화합니다.
@EnableJpaAuditing
public class Umc10thApplication {

    // 애플리케이션을 실행합니다.
    public static void main(String[] args) {
        SpringApplication.run(Umc10thApplication.class, args);
    }
}
