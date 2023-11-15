package com.springbot.blog.exception;

public class ResourceConflictException extends RuntimeException {
    public String resourceName;
    public String fieldName;
    public String fieldValue;

    public ResourceConflictException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s already exists with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
