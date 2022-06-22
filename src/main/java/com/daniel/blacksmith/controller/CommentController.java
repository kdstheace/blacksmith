package com.daniel.blacksmith.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.daniel.blacksmith.payload.CommentDto;
import com.daniel.blacksmith.service.CommentService;

import javax.validation.Valid;
import java.util.List;

@Api(value="CRUD REST API for Comment resources")
@RestController
@RequestMapping("/api")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @ApiOperation(value="Create comment")
    @PostMapping("/v1/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value="postId") Long postId
                                ,@Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }
    @ApiOperation(value="get ALL comment")
    @GetMapping("/v1/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable(value="postId") Long postId){
        return commentService.getCommentsByPostId(postId);
    }
    @ApiOperation(value="get comment by ID")
    @GetMapping("/v1/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value="postId") Long postId,
                                                     @PathVariable(value="commentId") Long commentId){
        CommentDto commentDto = commentService.getCommentById(postId, commentId);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }
    @ApiOperation(value="update comment")
    @PutMapping("/v1/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateCommentById(@PathVariable(value="postId") Long postId,
                                                        @PathVariable(value="commentId") Long commentId,
                                                        @Valid @RequestBody CommentDto commentDto){
        return ResponseEntity.ok(commentService.updateComment(postId, commentId, commentDto));
    }
    @ApiOperation(value="delete comment")
    @DeleteMapping("/v1/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable(value="postId") Long postId,
                                                @PathVariable(value="commentId") Long commentId){
        commentService.deleteCommentById(postId, commentId);
        return new ResponseEntity<>("Comment delete Successfully", HttpStatus.OK);
    }
}
