package com.library.demo.model.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class CreateBookRequest {
    @NotNull(message = "Field title cannot be null")
    @Getter
    @Setter
    private String title;


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
