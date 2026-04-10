package com.example.simpleblind.api.dto;

import com.example.simpleblind.application.dto.PopularPostResult;

import java.time.LocalDateTime;

public record PopularPostResponse(
        Long postId,
        String title,
        String authorNickname,
        String categoryName,
        Long likeCount,
        Long viewCount,
        Long score,
        LocalDateTime createdAt
) {
    public static PopularPostResponse from(PopularPostResult result) {
        return new PopularPostResponse(
                result.postId(),
                result.title(),
                result.authorNickname(),
                result.categoryName(),
                result.likeCount(),
                result.viewCount(),
                result.score(),
                result.createdAt()
        );
    }
}
