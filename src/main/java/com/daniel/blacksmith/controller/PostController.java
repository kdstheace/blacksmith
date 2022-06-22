package com.daniel.blacksmith.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.daniel.blacksmith.payload.PostDto;
import com.daniel.blacksmith.payload.PostResponse;
import com.daniel.blacksmith.service.PostService;
import com.daniel.blacksmith.utils.AppConstants;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class PostController {
    private PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    // @PreAuthorize("hasRole('ADMIN')") //ROLE_ADMIN    -- difference btw hasRole('a'), hasAuthority('a') the former adds 'ROLE_' as a prefix
    @PostMapping("/v1/posts")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping("/v1/posts")
    public PostResponse getAllPosts(
        @RequestParam(name="pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo
        , @RequestParam(name="pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize
        , @RequestParam(name="sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy
        , @RequestParam(name="sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR, required = false) String sortDir){
        return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
    }

    @GetMapping("/v1/posts/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") Long postId){
        return ResponseEntity.ok(postService.getPostById(postId));
        // return new ResponseEntity<>(postService.getPostById(postId), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/v1/posts/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable(name="id") Long postId,
                                              @Valid @RequestBody PostDto postDto){
        return ResponseEntity.ok(postService.updatePost(postId, postDto));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/v1/posts/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name="id") Long postId){
        postService.deletePost(postId);
        return new ResponseEntity<>("Post entity delete successfully", HttpStatus.OK);
    }
}
