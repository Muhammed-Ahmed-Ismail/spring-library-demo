package com.library.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
public class BorrowingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    @ManyToOne(cascade = CascadeType.REMOVE)
    Book book;

    @Setter
    @ManyToOne(cascade = CascadeType.REMOVE)
    Patron patron;

    @Setter
    @Column(nullable = false)
    Date borrowFrom;

    @Setter
    @Column
    Date borrowTo;
}
