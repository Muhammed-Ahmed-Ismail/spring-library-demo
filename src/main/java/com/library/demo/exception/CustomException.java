package com.library.demo.exception;

import lombok.Getter;

public class CustomException extends Exception{
    @Getter
    private final String field;
    public CustomException(String message,String field){
        super(message);
        this.field = field;
    }
}
