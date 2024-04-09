package com.library.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Long id;


    @Column(nullable = false)
    @JsonProperty
    @Getter
    @Setter
    private String name;

    @Column()
    @JsonProperty
    @Getter
    @Setter
    private String address;

    @Column(nullable = false,unique = true)
    @JsonProperty
    @Getter
    @Setter
    private String phone;

    @Column(nullable = false,unique = true)
    @JsonProperty
    @Getter
    @Setter
    private String email;
}
