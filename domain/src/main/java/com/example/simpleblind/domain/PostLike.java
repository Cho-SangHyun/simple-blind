package com.example.simpleblind.domain;

import jakarta.persistence.*;
import jakarta.persistence.ForeignKey;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "post_like",
    uniqueConstraints = @UniqueConstraint(columnNames = {"post_id", "user_id"}),
    indexes = @Index(name = "idx_post_like_created_at", columnList = "created_at")
)
public class PostLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private User user;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    protected PostLike() {}

    public PostLike(Post post, User user) {
        this.post = post;
        this.user = user;
        this.createdAt = LocalDateTime.now();
    }

    public PostLike(Post post, User user, LocalDateTime createdAt) {
        this.post = post;
        this.user = user;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public Post getPost() { return post; }
    public User getUser() { return user; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
