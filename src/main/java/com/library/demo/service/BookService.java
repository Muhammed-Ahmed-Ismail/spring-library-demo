package com.library.demo.service;

import com.library.demo.entity.Book;
import com.library.demo.exception.CustomException;
import com.library.demo.model.requests.book.CreateBookRequest;
import com.library.demo.model.requests.book.UpdateBookRequest;

import java.util.List;

public interface BookService {
    public Book getBook(Long id);
    public List<Book> listBooks();
    public Book createBook(CreateBookRequest request);
    public Book updateBook(UpdateBookRequest request , long pk);
    public void deleteBook(long pk);
}
