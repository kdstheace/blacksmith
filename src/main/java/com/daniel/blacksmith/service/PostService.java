package com.daniel.blacksmith.service;

import org.springframework.stereotype.Service;

import com.daniel.blacksmith.payload.PostDto;
import com.daniel.blacksmith.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
    PostDto getPostById(Long id);
    PostDto updatePost(Long id, PostDto postDto);
    void deletePost(Long id);
}
