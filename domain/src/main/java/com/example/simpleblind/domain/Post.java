package com.example.simpleblind.domain;

import jakarta.persistence.*;
import jakarta.persistence.ForeignKey;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "post",
    indexes = {
        @Index(name = "idx_post_category_id", columnList = "category_id"),
        @Index(name = "idx_post_created_at", columnList = "created_at")
    }
)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Category category;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private Long viewCount = 0L;

    @Column(nullable = false)
    private Long likeCount = 0L;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    private void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    protected Post() {}

    public Post(User user, Category category, String title, String content) {
        this.user = user;
        this.category = category;
        this.title = title;
        this.content = content;
    }

    public void incrementViewCount() { this.viewCount++; }
    public void incrementLikeCount() { this.likeCount++; }
    public void decrementLikeCount() { this.likeCount = Math.max(0, this.likeCount - 1); }

    public Long getId() { return id; }
    public User getUser() { return user; }
    public Category getCategory() { return category; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public Long getViewCount() { return viewCount; }
    public Long getLikeCount() { return likeCount; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
