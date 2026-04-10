package com.example.simpleblind.application.dto;

import com.example.simpleblind.domain.Post;

import java.time.LocalDateTime;

public record PostSummaryResult(
        Long id,
        String title,
        String authorNickname,
        Long viewCount,
        Long likeCount,
        LocalDateTime createdAt
) {
    public static PostSummaryResult from(Post post) {
        return new PostSummaryResult(
                post.getId(),
                post.getTitle(),
                post.getUser().getNickname(),
                post.getViewCount(),
                post.getLikeCount(),
                post.getCreatedAt()
        );
    }
}
