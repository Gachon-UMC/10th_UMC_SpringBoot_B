package com.example.umc10th.domain.term.dto;

import com.example.umc10th.domain.term.enums.TermName;
import lombok.Builder;

import java.time.LocalDateTime;

public class TermResDTO {
    @Builder
    public record TermItem(
            Long id,
            TermName termName,
            String content,
            Boolean optional,
            LocalDateTime createdAt
    ) {}

    @Builder
    public record MemberTermItem(
            Long memberTermId,
            Long termId,
            TermName termName,
            LocalDateTime agreedAt
    ) {}
}
