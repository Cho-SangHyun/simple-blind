package com.example.simpleblind.infra;

import com.example.simpleblind.domain.PopularPostSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PopularPostSnapshotRepository extends JpaRepository<PopularPostSnapshot, Long> {

    List<PopularPostSnapshot> findByTargetDate(LocalDate targetDate);

    void deleteByTargetDate(LocalDate targetDate);
}
