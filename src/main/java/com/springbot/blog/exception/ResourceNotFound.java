package com.springbot.blog.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException {
    public String resourceName;
    public String fieldName;
    public String fieldValue;

    public ResourceNotFound(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
    public String getResourceName() {
        return resourceName;
    }
    public String getFieldName() {
        return fieldName;
    }
    public String getFieldValue() {
        return fieldValue;
    }
}
