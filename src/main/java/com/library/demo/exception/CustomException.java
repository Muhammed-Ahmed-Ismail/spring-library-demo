package com.library.demo.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    private final String field;
    public CustomException(String message,String field){
        super(message);
        this.field = field;
    }
}
