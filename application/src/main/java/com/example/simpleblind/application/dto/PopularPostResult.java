package com.example.simpleblind.application.dto;

import com.example.simpleblind.infra.PopularPostProjection;

import java.time.LocalDateTime;

public record PopularPostResult(
        Long postId,
        String title,
        Long authorId,
        String authorNickname,
        Long categoryId,
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
                projection.getAuthorId(),
                projection.getAuthorNickname(),
                projection.getCategoryId(),
                projection.getCategoryName(),
                projection.getLikeCount(),
                projection.getViewCount(),
                projection.getScore(),
                projection.getCreatedAt()
        );
    }
}
