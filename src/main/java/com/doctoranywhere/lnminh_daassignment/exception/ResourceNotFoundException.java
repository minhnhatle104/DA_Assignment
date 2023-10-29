package com.doctoranywhere.lnminh_daassignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    private String resourceField;
    private String fieldName;
    private Long fieldValue;

    public ResourceNotFoundException(String resourceField,String fieldName,Long fieldValue){
        super(String.format("%s not found with %s: %s",resourceField,fieldName,fieldValue));
        this.resourceField = resourceField;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
