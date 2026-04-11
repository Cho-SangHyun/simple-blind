package com.example.simpleblind.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(
    name = "popular_post_snapshot",
    indexes = {
        @Index(name = "idx_snapshot_target_date", columnList = "target_date")
    }
)
public class PopularPostSnapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false)
    private Long authorId;

    @Column(nullable = false, length = 50)
    private String authorNickname;

    @Column(nullable = false)
    private Long categoryId;

    @Column(nullable = false, length = 50)
    private String categoryName;

    @Column(nullable = false)
    private Long likeCount;

    @Column(nullable = false)
    private Long viewCount;

    @Column(nullable = false)
    private Long score;

    @Column(nullable = false)
    private LocalDate targetDate;

    protected PopularPostSnapshot() {}

    public PopularPostSnapshot(Long postId, String title, Long authorId, String authorNickname,
                               Long categoryId, String categoryName,
                               Long likeCount, Long viewCount, Long score, LocalDate targetDate) {
        this.postId = postId;
        this.title = title;
        this.authorId = authorId;
        this.authorNickname = authorNickname;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.likeCount = likeCount;
        this.viewCount = viewCount;
        this.score = score;
        this.targetDate = targetDate;
    }

    public Long getId() { return id; }
    public Long getPostId() { return postId; }
    public String getTitle() { return title; }
    public Long getAuthorId() { return authorId; }
    public String getAuthorNickname() { return authorNickname; }
    public Long getCategoryId() { return categoryId; }
    public String getCategoryName() { return categoryName; }
    public Long getLikeCount() { return likeCount; }
    public Long getViewCount() { return viewCount; }
    public Long getScore() { return score; }
    public LocalDate getTargetDate() { return targetDate; }
}
