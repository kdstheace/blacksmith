package com.daniel.blacksmith.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.daniel.blacksmith.payload.PostDto;
import com.daniel.blacksmith.payload.PostResponse;
import com.daniel.blacksmith.service.PostService;
import com.daniel.blacksmith.utils.AppConstants;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping
    public PostResponse getAllPosts(
        @RequestParam(name="pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo
        , @RequestParam(name="pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize
        , @RequestParam(name="sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy
        , @RequestParam(name="sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR, required = false) String sortDir){
        return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") Long postId){
        return ResponseEntity.ok(postService.getPostById(postId));
        // return new ResponseEntity<>(postService.getPostById(postId), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable(name="id") Long postId,
                                              @RequestBody PostDto postDto){
        return ResponseEntity.ok(postService.updatePost(postId, postDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name="id") Long postId){
        postService.deletePost(postId);
        return new ResponseEntity<>("Post entity delete successfully", HttpStatus.OK);
    }



}
