package com.daniel.blacksmith.payload;


import lombok.Data;

@Data
public class PostDto {
    private Long postId;
    private String title;
    private String description;
    private String content;
}
