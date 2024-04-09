package com.library.demo.service;

import com.library.demo.entity.Book;
import com.library.demo.entity.BorrowingRecord;
import com.library.demo.entity.Patron;
import com.library.demo.exception.CustomException;
import com.library.demo.exception.notfound.BorrowingRecordNotFound;
import com.library.demo.repository.BookRepository;
import com.library.demo.repository.BorrowingRecordRepository;
import com.library.demo.repository.PatronRepository;
import com.library.demo.service.imp.BorrowingServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BorrowingServiceImplTests {
    @Mock
    BorrowingRecordRepository borrowingRecordRepository;
    @Mock
    BookRepository bookRepository;
    @Mock
    PatronRepository patronRepository;

    @InjectMocks
    BorrowingServiceImpl borrowingServiceImpl;

    @Test
    void borrowBook() {
        Book book1 = Book.builder()
                .id(1L)
                .isbn("1")
                .title("book1")
                .author("author1")
                .build();

        Book book2 = Book.builder()
                .id(2L)
                .isbn("2")
                .title("book2")
                .author("author2")
                .build();

        Patron patron = Patron.builder()
                .id(1L)
                .phone("123")
                .name("patron1")
                .email("patron@email.com")
                .build();

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book1));
        when(patronRepository.findById(1L)).thenReturn(Optional.of(patron));

        when(borrowingRecordRepository.existsByBookIdAndBorrowToIsNull(book1.getId())).thenReturn(Boolean.FALSE);

        BorrowingRecord borrowingRecord = BorrowingRecord.builder()
                .id(1L)
                .book(book1)
                .patron(patron)
                .borrowFrom(new Date())
                .build();

        when(borrowingRecordRepository.save(any())).thenReturn(borrowingRecord);

        BorrowingRecord savedBorrowingRecord = this.borrowingServiceImpl.borrowBook(patron.getId(), book1.getId());

        Assertions.assertNotNull(savedBorrowingRecord);

        when(borrowingRecordRepository.existsByBookIdAndBorrowToIsNull(book1.getId())).thenReturn(Boolean.TRUE);

        Assertions.assertThrows(CustomException.class, () -> borrowingServiceImpl.borrowBook(patron.getId(), book1.getId()));
    }

    @Test
    void returnBook() {
        Book book1 = Book.builder()
                .id(1L)
                .isbn("1")
                .title("book1")
                .author("author1")
                .build();

        Book book2 = Book.builder()
                .id(2L)
                .isbn("2")
                .title("book2")
                .author("author2")
                .build();

        Patron patron = Patron.builder()
                .id(1L)
                .phone("123")
                .name("patron1")
                .email("patron@email.com")
                .build();

        BorrowingRecord runningBorrowingRecord = BorrowingRecord.builder()
                .id(1L)
                .book(book1)
                .patron(patron)
                .borrowFrom(new Date())
                .build();

        BorrowingRecord closedBorrowingRecord = BorrowingRecord.builder()
                .id(1L)
                .book(book1)
                .patron(patron)
                .borrowFrom(new Date())
                .borrowTo(new Date())
                .build();

        when(borrowingRecordRepository.findByBookIdAndPatronIdAndBorrowToIsNull(book1.getId(), patron.getId())).thenReturn(Optional.of(runningBorrowingRecord));
        when(borrowingRecordRepository.findByBookIdAndPatronIdAndBorrowToIsNull(book2.getId(),patron.getId())).thenReturn(Optional.empty());

        when(borrowingRecordRepository.save(runningBorrowingRecord)).thenReturn(closedBorrowingRecord);

        BorrowingRecord record = this.borrowingServiceImpl.returnBook(patron.getId(),book1.getId());

        Assertions.assertNotNull(record);
        Assertions.assertThrows(BorrowingRecordNotFound.class,()-> this.borrowingServiceImpl.returnBook(patron.getId(), book2.getId()));
    }
}
