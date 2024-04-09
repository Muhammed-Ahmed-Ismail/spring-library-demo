package com.library.demo.validator.patron.phone.unique;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniquePhoneValidator.class)
public @interface UniquePhone {
    String message() default "Phone is already registered";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
