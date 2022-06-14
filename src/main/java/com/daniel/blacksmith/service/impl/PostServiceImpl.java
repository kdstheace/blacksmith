package com.daniel.blacksmith.service.impl;

import org.springframework.stereotype.Service;

import com.daniel.blacksmith.payload.PostDto;
import com.daniel.blacksmith.service.PostService;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    
    @Override
    public PostDto createPost(PostDto postDto) {

        return null;
    }

    @Override
    public List<PostDto> getAllPosts() {
        return null;
    }

    @Override
    public PostDto getPostById(Long id) {
        return null;
    }

    @Override
    public PostDto updatePost(Long id, PostDto postDto) {
        return null;
    }

    @Override
    public void deletePost(Long id) {

    }
}
