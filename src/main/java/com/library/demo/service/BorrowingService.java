package com.library.demo.service;

import com.library.demo.entity.BorrowingRecord;

public interface BorrowingService {
    public BorrowingRecord borrowBook(long patronId,long bookId);
    public BorrowingRecord returnBook(long patronId,long bookId);
}
