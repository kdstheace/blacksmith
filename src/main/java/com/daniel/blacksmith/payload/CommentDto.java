package com.daniel.blacksmith.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
@ApiModel(description = "Comment model information")
@Data
public class CommentDto {
    @ApiModelProperty(value="Comment id")
    private Long commentId;

    @ApiModelProperty(value="Comment name")
    @NotEmpty(message = "Name should not be null or empty")
    private String name;

    @ApiModelProperty(value="comment email")
    @NotEmpty(message = "Email should not be null or empty")
    @Email
    private String email;

    @ApiModelProperty(value="comment body")
    @NotEmpty
    @Size(min = 10, message = "Comment must be minimum 10 characters")
    private String body;
}
