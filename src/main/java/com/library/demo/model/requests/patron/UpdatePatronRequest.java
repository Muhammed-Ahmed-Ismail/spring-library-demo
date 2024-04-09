package com.library.demo.model.requests.patron;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdatePatronRequest {
    @NotNull
    private String name;

    @Email
    @NotNull
    private String email;

    @NotNull
    private String phone;

    private String address;
}
