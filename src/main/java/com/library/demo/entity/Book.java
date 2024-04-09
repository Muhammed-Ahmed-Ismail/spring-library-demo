package com.library.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Long id;


    @Column(nullable = false)
    @JsonProperty
    @Setter
    private String title;

    @Column(unique = true, nullable = false)
    @JsonProperty
    @Setter
    private String isbn;

    @Column(nullable = false)
    @JsonProperty
    @Setter
    private String author;

    @JsonProperty
    @Setter
    private String publicationYear;
}
