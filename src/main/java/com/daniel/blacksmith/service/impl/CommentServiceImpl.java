package com.daniel.blacksmith.service.impl;

import org.springframework.stereotype.Service;

import com.daniel.blacksmith.entity.Comment;
import com.daniel.blacksmith.entity.Post;
import com.daniel.blacksmith.exception.ResourceNotFoundException;
import com.daniel.blacksmith.payload.CommentDto;
import com.daniel.blacksmith.repository.CommentRepository;
import com.daniel.blacksmith.repository.PostRepository;
import com.daniel.blacksmith.service.CommentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository){
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);

        //1. retrieve Post entity by id
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));

        //2. set Post to Comment entity
        comment.setPost(post);

        //3. save Comment entity to DB
        Comment savedComment = commentRepository.save(comment);

        return mapToDto(savedComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(Long postId) {
        //retrieve comments by PostId
        List<Comment> commentList = commentRepository.findByPostId(postId);

        //convert list of comment entities to dto
        return commentList.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

    private CommentDto mapToDto(Comment comment){
        CommentDto commentDto = new CommentDto();
        commentDto.setCommentId(comment.getId());
        commentDto.setBody(comment.getBody());
        commentDto.setEmail(comment.getEmail());
        commentDto.setName(comment.getName());

        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto){
        Comment comment = new Comment();
        comment.setId(commentDto.getCommentId());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        comment.setName(commentDto.getName());
        return comment;
    }
}
