package com.example.simpleblind.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 인기글 관련 배치 작업 placeholder.
 * 추후 인기글 캐싱, 오래된 PostViewLog 정리 등의 작업을 여기에 추가.
 */
@Component
public class PopularPostBatchJob {

    private static final Logger log = LoggerFactory.getLogger(PopularPostBatchJob.class);

    // 매일 새벽 3시 실행 (placeholder)
    @Scheduled(cron = "0 0 3 * * *")
    public void cleanUpOldViewLogs() {
        log.info("[Batch] cleanUpOldViewLogs - not implemented yet");
    }
}
