package com.library.demo.service;

import com.library.demo.entity.Book;
import com.library.demo.model.requests.CreateBookRequest;

import java.util.List;

public interface BookService {
    public Book getBook(Long id);
    public List<Book> listBooks();
    public Book createBook(CreateBookRequest request);
}
