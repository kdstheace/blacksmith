package com.daniel.blacksmith.payload;

import java.util.Date;

public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;

    public ErrorDetails(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    // default format provided by Springboot
    // {
    //     "timestamp" : "2021-02-28T14:15:18.250+00:00",
    //     "status" : 404,
    //     "error" : "Not Found",
    //     "trace" : "com.springboot.blog.exception.ResourceNotFoundException: Post not found with id asdfdsa...",
    //     "message" : "Post not found with id asdfdsa...",
    //     "path" : "/api/posts/6"
    // }
}
