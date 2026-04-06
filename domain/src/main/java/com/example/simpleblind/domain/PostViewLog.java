package com.example.simpleblind.domain;

import jakarta.persistence.*;
import jakarta.persistence.ForeignKey;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "post_view_log",
    indexes = {
        @Index(name = "idx_post_view_log_viewed_at", columnList = "viewed_at"),
        @Index(name = "idx_post_view_log_post_id", columnList = "post_id")
    }
)
public class PostViewLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private User user;

    @Column(nullable = false)
    private LocalDateTime viewedAt;

    protected PostViewLog() {}

    public PostViewLog(Post post, User user) {
        this.post = post;
        this.user = user;
        this.viewedAt = LocalDateTime.now();
    }

    public PostViewLog(Post post, User user, LocalDateTime viewedAt) {
        this.post = post;
        this.user = user;
        this.viewedAt = viewedAt;
    }

    public Long getId() { return id; }
    public Post getPost() { return post; }
    public User getUser() { return user; }
    public LocalDateTime getViewedAt() { return viewedAt; }
}
