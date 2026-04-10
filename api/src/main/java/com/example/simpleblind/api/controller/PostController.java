package com.example.simpleblind.api.controller;

import com.example.simpleblind.api.dto.*;
import com.example.simpleblind.application.PostService;
import com.example.simpleblind.application.dto.LikeResult;
import com.example.simpleblind.application.dto.PostDetailResult;
import com.example.simpleblind.common.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/popular")
    public ApiResponse<List<PopularPostResponse>> findPopularPosts() {
        List<PopularPostResponse> response = postService.findPopularPosts().stream()
                .map(PopularPostResponse::from)
                .toList();
        return ApiResponse.ok(response);
    }

    @GetMapping("/{postId}")
    public ApiResponse<PostDetailResponse> findById(
            @PathVariable Long postId,
            @RequestParam(required = false) Long userId
    ) {
        PostDetailResult result = postService.findById(postId, userId);
        boolean liked = postService.isLikedByUser(postId, userId);
        return ApiResponse.ok(PostDetailResponse.from(result, liked));
    }

    @PostMapping
    public ApiResponse<PostDetailResponse> create(@RequestBody CreatePostRequest request) {
        PostDetailResult result = postService.create(request.userId(), request.categoryId(), request.title(), request.content());
        return ApiResponse.ok(PostDetailResponse.from(result, false));
    }

    @PostMapping("/{postId}/likes")
    public ApiResponse<PostLikeResponse> toggleLike(
            @PathVariable Long postId,
            @RequestBody PostLikeRequest request
    ) {
        LikeResult result = postService.toggleLike(postId, request.userId());
        return ApiResponse.ok(new PostLikeResponse(result.liked(), result.totalLikes()));
    }
}
