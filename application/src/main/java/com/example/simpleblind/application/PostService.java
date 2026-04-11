package com.example.simpleblind.application;

import com.example.simpleblind.application.dto.LikeResult;
import com.example.simpleblind.application.dto.PopularPostResult;
import com.example.simpleblind.application.dto.PostDetailResult;
import com.example.simpleblind.application.dto.PostSummaryResult;
import com.example.simpleblind.common.exception.EntityNotFoundException;
import com.example.simpleblind.domain.Category;
import com.example.simpleblind.domain.Post;
import com.example.simpleblind.domain.PostLike;
import com.example.simpleblind.domain.PostViewLog;
import com.example.simpleblind.domain.User;
import com.example.simpleblind.infra.CategoryRepository;
import com.example.simpleblind.infra.PostLikeRepository;
import com.example.simpleblind.infra.PostRepository;
import com.example.simpleblind.infra.PostViewLogRepository;
import com.example.simpleblind.infra.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;
    private final PostViewLogRepository postViewLogRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final PopularPostSnapshotService popularPostSnapshotService;

    public PostService(PostRepository postRepository,
                       PostLikeRepository postLikeRepository,
                       PostViewLogRepository postViewLogRepository,
                       UserRepository userRepository,
                       CategoryRepository categoryRepository,
                       PopularPostSnapshotService popularPostSnapshotService) {
        this.postRepository = postRepository;
        this.postLikeRepository = postLikeRepository;
        this.postViewLogRepository = postViewLogRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.popularPostSnapshotService = popularPostSnapshotService;
    }

    public Page<PostSummaryResult> findByCategoryId(Long categoryId, Pageable pageable) {
        return postRepository.findByCategoryIdWithUser(categoryId, pageable)
                .map(PostSummaryResult::from);
    }

    @Transactional
    public PostDetailResult findById(Long postId, Long userId) {
        Post post = postRepository.findByIdWithAssociations(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found: " + postId));

        post.incrementViewCount();

        User user = (userId != null)
                ? userRepository.findById(userId).orElse(null)
                : null;
        postViewLogRepository.save(new PostViewLog(post, user));

        return PostDetailResult.from(post);
    }

    public boolean isLikedByUser(Long postId, Long userId) {
        if (userId == null) return false;
        return postLikeRepository.existsByPostIdAndUserId(postId, userId);
    }

    @Transactional
    public PostDetailResult create(Long userId, Long categoryId, String title, String content) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + userId));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found: " + categoryId));

        return PostDetailResult.from(postRepository.save(new Post(user, category, title, content)));
    }

    @Transactional
    public LikeResult toggleLike(Long postId, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found: " + postId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + userId));

        Optional<PostLike> existing = postLikeRepository.findByPostIdAndUserId(postId, userId);

        if (existing.isPresent()) {
            postLikeRepository.delete(existing.get());
            post.decrementLikeCount();
            return new LikeResult(false, post.getLikeCount());
        } else {
            postLikeRepository.save(new PostLike(post, user));
            post.incrementLikeCount();
            return new LikeResult(true, post.getLikeCount());
        }
    }

    public List<PopularPostResult> findPopularPosts() {
        return popularPostSnapshotService.findLatest();
    }
}
