package com.example.simpleblind.batch;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Endpoint(id = "runPopularPostBatch")
public class PopularPostBatchEndpoint {

    private final PopularPostBatchJob batchJob;

    public PopularPostBatchEndpoint(PopularPostBatchJob batchJob) {
        this.batchJob = batchJob;
    }

    @WriteOperation
    public String run() {
        batchJob.generatePopularPostSnapshot();
        return "Popular post snapshot generated for " + LocalDate.now().minusDays(1);
    }
}
