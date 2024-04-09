package com.library.demo.repository;

import com.library.demo.entity.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord,Long> {

    public BorrowingRecord findByBookIdAndBorrowToIsNull(long bookId);
    public Optional <BorrowingRecord> findByBookIdAndPatronIdAndBorrowToIsNull(long bookId, long patronId);
}
