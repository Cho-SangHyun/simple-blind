package com.example.simpleblind.api.dto;

import com.example.simpleblind.application.dto.CategoryResult;

import java.time.LocalDateTime;

public record CategoryResponse(Long id, String name, LocalDateTime createdAt) {

    public static CategoryResponse from(CategoryResult result) {
        return new CategoryResponse(result.id(), result.name(), result.createdAt());
    }
}
