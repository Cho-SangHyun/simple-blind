package com.example.simpleblind.application.dto;

import com.example.simpleblind.domain.Post;

import java.time.LocalDateTime;

public record PostDetailResult(
        Long id,
        String title,
        String content,
        String authorNickname,
        String categoryName,
        Long viewCount,
        Long likeCount,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static PostDetailResult from(Post post) {
        return new PostDetailResult(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getUser().getNickname(),
                post.getCategory().getName(),
                post.getViewCount(),
                post.getLikeCount(),
                post.getCreatedAt(),
                post.getUpdatedAt()
        );
    }
}
