package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404_1", "해당 리뷰를 찾을 수 없습니다. "),
    REVIEW_ALREADY_EXISTS(HttpStatus.CONFLICT, "REVIEW409_1", "이미 리뷰를 작성했습니다. "),
    REVIEW_FORBIDDEN(HttpStatus.FORBIDDEN, "REVIEW403_1", "해당 리뷰에 접근할 권한이 없습니다. "),
    REVIEW_NOT_WRITER(HttpStatus.FORBIDDEN, "REVIEW403_2", "리뷰 작성자만 수정 또는 삭제할 수 있습니다. "),
    REVIEW_INVALID_RATING(HttpStatus.BAD_REQUEST, "REVIEW400_1", "리뷰 평점은 허용된 범위 안에서 입력해야 합니다. "),
    REVIEW_CONTENT_EMPTY(HttpStatus.BAD_REQUEST, "REVIEW400_2", "리뷰 내용을 입력해야 합니다. "),
    REVIEW_IMAGE_LIMIT_EXCEEDED(HttpStatus.BAD_REQUEST, "REVIEW400_3", "리뷰 이미지는 최대 개수를 초과할 수 없습니다. ");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
