package com.example.simpleblind.infra;

import com.example.simpleblind.domain.PostViewLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostViewLogRepository extends JpaRepository<PostViewLog, Long> {
}
