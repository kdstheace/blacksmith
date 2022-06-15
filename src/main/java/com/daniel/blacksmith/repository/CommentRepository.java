package com.daniel.blacksmith.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daniel.blacksmith.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);
    // Comment findByPostBy
}
