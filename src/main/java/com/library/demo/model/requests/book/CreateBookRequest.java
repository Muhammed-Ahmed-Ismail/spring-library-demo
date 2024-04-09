package com.library.demo.model.requests.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import validator.isnb.unique.UniqueIsbn;

public class CreateBookRequest {
    @NotNull(message = "Field title cannot be null")
    @Getter
    @Setter
    private String title;

    @UniqueIsbn(message = "Field isbn must be unique")
    @NotNull(message = "Field isbn cannot be null")
    @JsonProperty
    @Getter @Setter
    private String isbn;

    @NotNull(message = "Field author cannot be null")
    @JsonProperty
    @Getter @Setter
    private String author;

    @NotNull(message = "Field publicationYear cannot be null")
    @JsonProperty
    @Getter @Setter
    private String publicationYear;
}
