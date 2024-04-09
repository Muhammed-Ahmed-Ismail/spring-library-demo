package com.library.demo.model.requests.patron;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import validator.patron.email.unique.UniqueEmail;
import validator.patron.phone.unique.UniquePhone;

@Setter
@Getter
public class CreatePatronRequest {
    @NotNull
    private String name;

    @UniqueEmail
    @Email
    @NotNull
    private String email;

    @UniquePhone
    @NotNull
    private String phone;

    private String address;
}
