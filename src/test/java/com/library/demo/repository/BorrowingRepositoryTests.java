package com.library.demo.repository;

import com.library.demo.entity.Book;
import com.library.demo.entity.BorrowingRecord;
import com.library.demo.entity.Patron;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class BorrowingRepositoryTests {

    @Autowired
    BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    PatronRepository patronRepository;

    @Test
    void existByBookIdAndBorrowToIsNull() {
        // Arrange
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

        BorrowingRecord borrowingRecord = BorrowingRecord.builder()
                .id(1L)
                .book(book1)
                .patron(patron)
                .borrowFrom(new Date())
                .build();

        // Act
        this.bookRepository.save(book1);
        this.bookRepository.save(book2);
        this.patronRepository.save(patron);

        this.borrowingRecordRepository.save(borrowingRecord);

        // Assert

        boolean IsThereARunningRecordForBook1 = this.borrowingRecordRepository.existsByBookIdAndBorrowToIsNull(book1.getId());
        boolean IsThereARunningRecordForPatron = this.borrowingRecordRepository.existsByPatronIdAndBorrowToIsNull(patron.getId());
        boolean IsThereARunningRecordForBook2 = this.borrowingRecordRepository.existsByBookIdAndBorrowToIsNull(book2.getId());

        Assertions.assertTrue(IsThereARunningRecordForBook1);
        Assertions.assertTrue(IsThereARunningRecordForPatron);
        Assertions.assertFalse(IsThereARunningRecordForBook2);


    }
}
