package com.library.demo.controller;

import com.library.demo.entity.Book;
import com.library.demo.exception.CustomException;
import com.library.demo.model.requests.book.CreateBookRequest;
import com.library.demo.model.requests.book.UpdateBookRequest;
import com.library.demo.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody CreateBookRequest request) {
        Book book = this.bookService.createBook(request);
        return new ResponseEntity<Book>(book, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(this.bookService.listBooks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable long id){
        return new ResponseEntity<>(this.bookService.getBook(id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable long id , @RequestBody UpdateBookRequest request){
        return new ResponseEntity<Book>(this.bookService.updateBook(request,id),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBook(@PathVariable long id){
        this.bookService.deleteBook(id);
        return new ResponseEntity<>("book deleted successfully",HttpStatus.OK);
    }
}
