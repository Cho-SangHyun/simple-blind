package com.example.simpleblind.infra;

import java.time.LocalDateTime;

public interface PopularPostProjection {
    Long getPostId();
    String getTitle();
    Long getAuthorId();
    String getAuthorNickname();
    Long getCategoryId();
    String getCategoryName();
    Long getLikeCount();
    Long getViewCount();
    Long getScore();
    LocalDateTime getCreatedAt();
}
