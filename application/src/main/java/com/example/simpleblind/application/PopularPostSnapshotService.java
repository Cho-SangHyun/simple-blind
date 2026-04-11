package com.example.simpleblind.application;

import com.example.simpleblind.application.dto.PopularPostResult;
import com.example.simpleblind.domain.PopularPostSnapshot;
import com.example.simpleblind.infra.PopularPostSnapshotRepository;
import com.example.simpleblind.infra.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PopularPostSnapshotService {

    private final PopularPostSnapshotRepository snapshotRepository;
    private final PostRepository postRepository;

    public PopularPostSnapshotService(PopularPostSnapshotRepository snapshotRepository,
                                      PostRepository postRepository) {
        this.snapshotRepository = snapshotRepository;
        this.postRepository = postRepository;
    }

    @Transactional
    public void generateSnapshot(LocalDate targetDate) {
        LocalDateTime start = targetDate.atStartOfDay();
        LocalDateTime end = targetDate.plusDays(1).atStartOfDay();

        snapshotRepository.deleteByTargetDate(targetDate);

        List<PopularPostSnapshot> snapshots = postRepository.findPopularPosts(start, end).stream()
                .map(p -> new PopularPostSnapshot(
                        p.getPostId(),
                        p.getTitle(),
                        p.getAuthorId(),
                        p.getAuthorNickname(),
                        p.getCategoryId(),
                        p.getCategoryName(),
                        p.getLikeCount(),
                        p.getViewCount(),
                        p.getScore(),
                        targetDate
                ))
                .toList();

        snapshotRepository.saveAll(snapshots);
    }

    @Transactional(readOnly = true)
    public List<PopularPostResult> findLatest() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        List<PopularPostSnapshot> snapshots = snapshotRepository.findByTargetDate(yesterday);

        if (!snapshots.isEmpty()) {
            return snapshots.stream()
                    .map(s -> new PopularPostResult(
                            s.getPostId(),
                            s.getTitle(),
                            s.getAuthorId(),
                            s.getAuthorNickname(),
                            s.getCategoryId(),
                            s.getCategoryName(),
                            s.getLikeCount(),
                            s.getViewCount(),
                            s.getScore(),
                            null
                    ))
                    .toList();
        }

        // fallback: 스냅샷 없으면 native query 직접 실행
        LocalDateTime start = yesterday.atStartOfDay();
        LocalDateTime end = LocalDate.now().atStartOfDay();
        return postRepository.findPopularPosts(start, end).stream()
                .map(PopularPostResult::from)
                .toList();
    }
}
