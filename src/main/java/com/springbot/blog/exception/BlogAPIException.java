package com.springbot.blog.exception;

import org.springframework.http.HttpStatus;

public class BlogAPIException extends RuntimeException {
   private HttpStatus status;
    private String message;

    public BlogAPIException( HttpStatus status,String message) {
        super(message);
        this.status = status;
    }

    public BlogAPIException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
