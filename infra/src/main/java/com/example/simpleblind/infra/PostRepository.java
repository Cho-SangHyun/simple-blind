package com.example.simpleblind.infra;

import com.example.simpleblind.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT p FROM Post p JOIN FETCH p.user WHERE p.category.id = :categoryId ORDER BY p.createdAt DESC",
           countQuery = "SELECT COUNT(p) FROM Post p WHERE p.category.id = :categoryId")
    Page<Post> findByCategoryIdWithUser(@Param("categoryId") Long categoryId, Pageable pageable);

    @Query("SELECT p FROM Post p JOIN FETCH p.user JOIN FETCH p.category WHERE p.id = :id")
    Optional<Post> findByIdWithAssociations(@Param("id") Long id);

    @Query(value = """
            SELECT p.id        AS postId,
                   p.title     AS title,
                   u.nickname  AS authorNickname,
                   c.name      AS categoryName,
                   COALESCE(l.like_count, 0)  AS likeCount,
                   COALESCE(v.view_count, 0)  AS viewCount,
                   (COALESCE(l.like_count, 0) * 3 + COALESCE(v.view_count, 0)) AS score,
                   p.created_at AS createdAt
            FROM post p
            JOIN users u    ON p.user_id     = u.id
            JOIN category c ON p.category_id = c.id
            JOIN (
                SELECT post_id, COUNT(*) AS like_count
                FROM post_like
                WHERE created_at >= :start AND created_at < :end
                GROUP BY post_id
            ) l ON p.id = l.post_id
            JOIN (
                SELECT post_id, COUNT(*) AS view_count
                FROM post_view_log
                WHERE viewed_at >= :start AND viewed_at < :end
                GROUP BY post_id
            ) v ON p.id = v.post_id
            HAVING score > 0
            ORDER BY score DESC
            LIMIT 50
            """,
            nativeQuery = true)
    List<PopularPostProjection> findPopularPosts(@Param("start") LocalDateTime start,
                                                 @Param("end") LocalDateTime end);
}
