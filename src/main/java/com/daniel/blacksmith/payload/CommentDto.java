package com.daniel.blacksmith.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CommentDto {
    private Long commentId;
    private String name;
    private String email;
    private String body;
}
