package com.daniel.blacksmith.payload;


import lombok.Data;

import java.util.Set;

@Data
public class PostDto {
    private Long postId;
    private String title;
    private String description;
    private String content;
    private Set<CommentDto> commentSet;
}
