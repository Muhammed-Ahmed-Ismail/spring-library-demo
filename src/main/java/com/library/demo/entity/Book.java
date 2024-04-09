package com.library.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Long id;


    @Column(nullable = false)
    @JsonProperty
    @Getter
    @Setter
    private String title;

    @Column(unique = true, nullable = false)
    @JsonProperty
    @Getter
    @Setter
    private String isbn;

    @Column(nullable = false)
    @JsonProperty
    @Getter
    @Setter
    private String author;

    @JsonProperty
    @Getter
    @Setter
    private String publicationYear;
}
