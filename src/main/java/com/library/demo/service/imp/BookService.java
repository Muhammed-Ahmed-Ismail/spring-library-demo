package com.library.demo.service.imp;

import com.library.demo.entity.Book;
import com.library.demo.exception.notfound.BookNotFound;
import com.library.demo.exception.CustomException;
import com.library.demo.model.requests.book.CreateBookRequest;
import com.library.demo.model.requests.book.UpdateBookRequest;
import com.library.demo.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookService implements com.library.demo.service.BookService {

    final String BOOK_IS_NOT_FOUND = "Book is not found";
    BookRepository bookRepository;
    ModelMapper modelMapper;

    @Autowired
    public BookService(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Book getBook(Long id) {
        Optional<Book> optionalBook = this.bookRepository.findById(id);
        Book book = optionalBook.orElseThrow(() -> new BookNotFound(BOOK_IS_NOT_FOUND));

        return book;
    }

    @Override
    public List<Book> listBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book createBook(CreateBookRequest request) {

        Book book = modelMapper.map(request, Book.class);


        this.bookRepository.save(book);


        return book;
    }

    @Override
    public Book updateBook(UpdateBookRequest request, long pk) throws CustomException {
        Optional<Book> optionalBook = this.bookRepository.findById(pk);
        Book book = optionalBook.orElseThrow(() -> new BookNotFound(BOOK_IS_NOT_FOUND));
        if (!Objects.equals(book.getIsbn(), request.getIsbn())) {
            if (this.bookRepository.existsByIsbn(request.getIsbn()))
                throw new CustomException("there is already another book with this isbn", "isbn");
        }
        this.modelMapper.map(request, book);
        this.bookRepository.save(book);
        return book;
    }
}
