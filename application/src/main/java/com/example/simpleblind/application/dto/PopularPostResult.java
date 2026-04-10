package com.example.simpleblind.application.dto;

import com.example.simpleblind.infra.PopularPostProjection;

import java.time.LocalDateTime;

public record PopularPostResult(
        Long postId,
        String title,
        String authorNickname,
        String categoryName,
        Long likeCount,
        Long viewCount,
        Long score,
        LocalDateTime createdAt
) {
    public static PopularPostResult from(PopularPostProjection projection) {
        return new PopularPostResult(
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
