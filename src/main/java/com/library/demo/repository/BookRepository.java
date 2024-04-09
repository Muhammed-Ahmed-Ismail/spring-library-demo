package com.library.demo.repository;

import com.library.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    public boolean existsByIsbn(String isbn);
}
