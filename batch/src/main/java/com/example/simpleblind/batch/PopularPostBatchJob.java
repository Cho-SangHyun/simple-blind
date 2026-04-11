package com.example.simpleblind.batch;

import com.example.simpleblind.application.PopularPostSnapshotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PopularPostBatchJob {

    private static final Logger log = LoggerFactory.getLogger(PopularPostBatchJob.class);

    private final PopularPostSnapshotService snapshotService;

    public PopularPostBatchJob(PopularPostSnapshotService snapshotService) {
        this.snapshotService = snapshotService;
    }

    @Scheduled(cron = "0 0 3 * * *")
    public void generatePopularPostSnapshot() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        log.info("[Batch] 인기글 스냅샷 생성 시작 - targetDate: {}", yesterday);
        snapshotService.generateSnapshot(yesterday);
        log.info("[Batch] 인기글 스냅샷 생성 완료 - targetDate: {}", yesterday);
    }
}
