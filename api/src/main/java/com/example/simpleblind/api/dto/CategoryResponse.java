package com.example.simpleblind.api.dto;

import com.example.simpleblind.domain.Category;

import java.time.LocalDateTime;

public record CategoryResponse(Long id, String name, LocalDateTime createdAt) {

    public static CategoryResponse from(Category category) {
        return new CategoryResponse(category.getId(), category.getName(), category.getCreatedAt());
    }
}
