package com.library.demo.exception.notfound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class BorrowingRecordNotFound extends RuntimeException{
    public BorrowingRecordNotFound(String message){
        super(message);
    }
}
