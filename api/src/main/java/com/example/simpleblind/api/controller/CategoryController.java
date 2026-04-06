package com.example.simpleblind.api.controller;

import com.example.simpleblind.api.dto.CategoryResponse;
import com.example.simpleblind.api.dto.PostSummaryResponse;
import com.example.simpleblind.application.CategoryService;
import com.example.simpleblind.application.PostService;
import com.example.simpleblind.common.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final PostService postService;

    public CategoryController(CategoryService categoryService, PostService postService) {
        this.categoryService = categoryService;
        this.postService = postService;
    }

    @GetMapping
    public ApiResponse<List<CategoryResponse>> findAll() {
        List<CategoryResponse> response = categoryService.findAll().stream()
                .map(CategoryResponse::from)
                .toList();
        return ApiResponse.ok(response);
    }

    @GetMapping("/{categoryId}/posts")
    public ApiResponse<Page<PostSummaryResponse>> findPosts(
            @PathVariable Long categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        Page<PostSummaryResponse> response = postService
                .findByCategoryId(categoryId, PageRequest.of(page, size))
                .map(PostSummaryResponse::from);
        return ApiResponse.ok(response);
    }
}
