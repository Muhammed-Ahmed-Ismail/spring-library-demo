package com.library.demo.validator.book.isnb.unique;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueIsbnValidator.class)

public @interface UniqueIsbn {
    String message() default "ISBN is already registered";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
