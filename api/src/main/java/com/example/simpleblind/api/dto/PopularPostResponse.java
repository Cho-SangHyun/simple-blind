package com.example.simpleblind.api.dto;

import com.example.simpleblind.infra.PopularPostProjection;

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
    public static PopularPostResponse from(PopularPostProjection projection) {
        return new PopularPostResponse(
                projection.getPostId(),
                projection.getTitle(),
                projection.getAuthorNickname(),
                projection.getCategoryName(),
                projection.getLikeCount(),
                projection.getViewCount(),
                projection.getScore(),
                projection.getCreatedAt()
        );
    }
}
