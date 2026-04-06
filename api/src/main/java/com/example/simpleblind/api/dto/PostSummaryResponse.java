package com.example.simpleblind.api.dto;

import com.example.simpleblind.domain.Post;

import java.time.LocalDateTime;

public record PostSummaryResponse(
        Long id,
        String title,
        String authorNickname,
        Long viewCount,
        Long likeCount,
        LocalDateTime createdAt
) {
    public static PostSummaryResponse from(Post post) {
        return new PostSummaryResponse(
                post.getId(),
                post.getTitle(),
                post.getUser().getNickname(),
                post.getViewCount(),
                post.getLikeCount(),
                post.getCreatedAt()
        );
    }
}
