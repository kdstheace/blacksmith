package com.daniel.blacksmith.payload;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@ApiModel(description = "Post model Information")
@Data
public class PostDto {
    @ApiModelProperty(value = "Blog post id")
    private Long postId;
    //title should not be null or empty
    //title should have at least 2 characters

    @ApiModelProperty(value = "Blog post title")
    @NotEmpty
    @Size(min = 2, message="Post title should have at least 2 characters")
    private String title;

    //description should not be null or empty
    //description should have at least 10 characters
    @ApiModelProperty(value = "Blog post description")
    @NotEmpty
    @Size(min = 2, message="Post description should have at least 10 characters")
    private String description;

    //content should not be null or empty
    @ApiModelProperty(value = "Blog post contents")
    @NotEmpty
    private String content;

    @ApiModelProperty(value = "Blog post comments")
    private Set<CommentDto> commentSet;
}
