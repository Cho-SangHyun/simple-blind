package com.example.simpleblind.api.dto;

import com.example.simpleblind.domain.Post;

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
    public static PostDetailResponse from(Post post, boolean likedByCurrentUser) {
        return new PostDetailResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getUser().getNickname(),
                post.getCategory().getName(),
                post.getViewCount(),
                post.getLikeCount(),
                likedByCurrentUser,
                post.getCreatedAt(),
                post.getUpdatedAt()
        );
    }
}
