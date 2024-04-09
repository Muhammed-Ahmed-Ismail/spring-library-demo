package com.library.demo.service.imp;

import com.library.demo.entity.Book;
import com.library.demo.exception.BookNotFound;
import com.library.demo.model.requests.CreateBookRequest;
import com.library.demo.repository.BookRepository;
import com.library.demo.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImp implements BookService {

    BookRepository bookRepository;
    ModelMapper modelMapper;

    @Autowired
    public BookServiceImp(BookRepository bookRepository , ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Book getBook(Long id) {
        Optional<Book> optionalBook =  this.bookRepository.findById(id);
        Book book = optionalBook.orElseThrow(() -> new BookNotFound("Book is not found"));
        return book;
    }

    @Override
    public List<Book> listBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book createBook(CreateBookRequest request) {

        Book book = modelMapper.map(request,Book.class);

        this.bookRepository.save(book);

        return book;
    }
}
