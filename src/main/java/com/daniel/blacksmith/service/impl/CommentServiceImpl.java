package com.daniel.blacksmith.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.daniel.blacksmith.entity.Comment;
import com.daniel.blacksmith.entity.Post;
import com.daniel.blacksmith.exception.BlogAPIException;
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
    private ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper modelMapper){
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
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

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        //Retrieve Post Entity by Id
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        //Retrieve comment by Id
        Comment comment = commentRepository.findById(commentId)
            .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
        post.getCommentSet().add(comment);
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to Post");
        }

        return mapToDto(comment);
    }

    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto) {
        //Retrieve Post Entity by Id
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));

        //Retrieve comment by Id
        Comment comment = commentRepository.findById(commentId)
            .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
        post.getCommentSet().add(comment);
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to Post");
        }

        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        Comment updatedComment = commentRepository.save(comment);

        return mapToDto(updatedComment);

    }

    @Override
    public void deleteCommentById(Long postId, Long commentId) {
        //Retrieve Post Entity by Id
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));

        //Retrieve comment by Id
        Comment comment = commentRepository.findById(commentId)
            .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
        post.getCommentSet().add(comment);
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to Post");
        }

        commentRepository.delete(comment);

    }

    private CommentDto mapToDto(Comment comment){
        CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto){
        Comment comment = modelMapper.map(commentDto, Comment.class);
        return comment;
    }
}
