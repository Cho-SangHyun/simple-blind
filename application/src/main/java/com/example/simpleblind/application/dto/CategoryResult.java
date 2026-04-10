package com.example.simpleblind.application.dto;

import com.example.simpleblind.domain.Category;

import java.time.LocalDateTime;

public record CategoryResult(Long id, String name, LocalDateTime createdAt) {

    public static CategoryResult from(Category category) {
        return new CategoryResult(category.getId(), category.getName(), category.getCreatedAt());
    }
}
