package com.library.demo.controller;

import com.library.demo.entity.BorrowingRecord;
import com.library.demo.service.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BorrowingController {
    @Autowired
    BorrowingService borrowingService;

    @PostMapping("/api/borrow/{bookId}/patron/{patronId}")
    ResponseEntity<BorrowingRecord> borrow(@PathVariable long bookId, @PathVariable long patronId){
        return new ResponseEntity<>(this.borrowingService.borrowBook(patronId,bookId), HttpStatus.OK);
    }

    @PutMapping("/api/return/{bookId}/patron/{patronId}")
    ResponseEntity<BorrowingRecord> returnBook(@PathVariable long bookId, @PathVariable long patronId){
        return new ResponseEntity<>(this.borrowingService.returnBook(patronId,bookId),HttpStatus.OK );
    }
}
