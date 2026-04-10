package com.example.simpleblind.api.dto;

import com.example.simpleblind.application.dto.PostDetailResult;

import java.time.LocalDateTime;

public record PostDetailResponse(
        Long id,
        String title,
        String content,
        String authorNickname,
        String categoryName,
        Long viewCount,
        Long likeCount,
        boolean likedByCurrentUser,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static PostDetailResponse from(PostDetailResult result, boolean likedByCurrentUser) {
        return new PostDetailResponse(
                result.id(),
                result.title(),
                result.content(),
                result.authorNickname(),
                result.categoryName(),
                result.viewCount(),
                result.likeCount(),
                likedByCurrentUser,
                result.createdAt(),
                result.updatedAt()
        );
    }
}
