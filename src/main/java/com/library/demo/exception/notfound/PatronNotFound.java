package com.library.demo.exception.notfound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)

public class PatronNotFound extends RuntimeException{
    public PatronNotFound(String message){
        super(message);
    }
}
