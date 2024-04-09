package com.library.demo.validator.patron.email.unique;

import com.library.demo.repository.PatronRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail,String> {
    @Autowired
    PatronRepository patronRepository;
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {

        return !this.patronRepository.existsByEmail(email);
    }
}
