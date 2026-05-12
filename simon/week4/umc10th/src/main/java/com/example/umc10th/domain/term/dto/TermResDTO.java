package com.example.umc10th.domain.term.dto;

import com.example.umc10th.domain.term.enums.TermName;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

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
    public record TermList(
            List<TermItem> terms,
            int page,
            int size,
            int totalPage,
            long totalElements,
            boolean isFirst,
            boolean isLast
    ) {}

    @Builder
    public record MemberTermItem(
            Long memberTermId,
            Long termId,
            TermName termName,
            LocalDateTime agreedAt
    ) {}

    @Builder
    public record MemberTermList(
            List<MemberTermItem> items,
            int page,
            int size,
            int totalPage,
            long totalElements,
            boolean isFirst,
            boolean isLast
    ) {}
}
