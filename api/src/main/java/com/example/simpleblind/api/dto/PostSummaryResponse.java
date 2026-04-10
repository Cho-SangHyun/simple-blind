package com.example.simpleblind.api.dto;

import com.example.simpleblind.application.dto.PostSummaryResult;

import java.time.LocalDateTime;

public record PostSummaryResponse(
        Long id,
        String title,
        String authorNickname,
        Long viewCount,
        Long likeCount,
        LocalDateTime createdAt
) {
    public static PostSummaryResponse from(PostSummaryResult result) {
        return new PostSummaryResponse(
                result.id(),
                result.title(),
                result.authorNickname(),
                result.viewCount(),
                result.likeCount(),
                result.createdAt()
        );
    }
}
