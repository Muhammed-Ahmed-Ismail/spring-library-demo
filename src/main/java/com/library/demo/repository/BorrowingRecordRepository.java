package com.library.demo.repository;

import com.library.demo.entity.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord,Long> {

    boolean existsByBookIdAndBorrowToIsNull(long bookId);
    boolean existsByPatronIdAndBorrowToIsNull(long patronId);
    Optional <BorrowingRecord> findByBookIdAndPatronIdAndBorrowToIsNull(long bookId, long patronId);
}
