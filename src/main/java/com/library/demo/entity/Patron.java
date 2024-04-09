package com.library.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Long id;


    @Column(nullable = false)
    @JsonProperty
    @Setter
    private String name;

    @Column()
    @JsonProperty
    @Setter
    private String address;

    @Column(nullable = false,unique = true)
    @JsonProperty
    @Setter
    private String phone;

    @Column(nullable = false,unique = true)
    @JsonProperty
    @Setter
    private String email;
}
