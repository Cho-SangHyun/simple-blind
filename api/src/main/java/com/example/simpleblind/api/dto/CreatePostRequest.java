package com.example.simpleblind.api.dto;

public record CreatePostRequest(Long userId, Long categoryId, String title, String content) {}
