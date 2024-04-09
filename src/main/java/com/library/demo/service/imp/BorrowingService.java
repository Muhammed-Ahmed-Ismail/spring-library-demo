package com.library.demo.service.imp;

import com.library.demo.entity.Book;
import com.library.demo.entity.BorrowingRecord;
import com.library.demo.entity.Patron;
import com.library.demo.exception.notfound.BookNotFound;
import com.library.demo.exception.CustomException;
import com.library.demo.exception.notfound.BorrowingRecordNotFound;
import com.library.demo.exception.notfound.PatronNotFound;
import com.library.demo.repository.BookRepository;
import com.library.demo.repository.BorrowingRecordRepository;
import com.library.demo.repository.PatronRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class BorrowingService implements com.library.demo.service.BorrowingService {
    private final String BOOK_NOT_FOUND = "Book not found";
    private final String PATRON_NOT_FOUND = "Patron not found";

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PatronRepository patronRepository;

    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;


    @Transactional
    @Override
    public BorrowingRecord borrowBook(long patronId, long bookId) {
        Optional<Book> optionalBook = this.bookRepository.findById(bookId);
        Book book = optionalBook.orElseThrow(() -> new BookNotFound(BOOK_NOT_FOUND));

        Optional<Patron> optionalPatron = this.patronRepository.findById(patronId);
        Patron patron = optionalPatron.orElseThrow(() -> new PatronNotFound(PATRON_NOT_FOUND));

        var IsThereRunningBorrowingRecordForBook = this.borrowingRecordRepository.existsByBookIdAndBorrowToIsNull(bookId);
        if (IsThereRunningBorrowingRecordForBook)
            throw new CustomException("Book is already borrowed", "book");

        var borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowFrom(new Date());

        this.borrowingRecordRepository.save(borrowingRecord);

        return borrowingRecord;
    }

    @Transactional
    @Override
    public BorrowingRecord returnBook(long patronId, long bookId) {
        Optional<BorrowingRecord> optionalBorrowingRecord = this.borrowingRecordRepository.findByBookIdAndPatronIdAndBorrowToIsNull(bookId, patronId);
        BorrowingRecord borrowingRecord = optionalBorrowingRecord.orElseThrow(() -> new BorrowingRecordNotFound("No open borrowing record found for this patron and this book"));

        borrowingRecord.setBorrowTo(new Date());
        this.borrowingRecordRepository.save(borrowingRecord);
        return borrowingRecord;
    }
}
