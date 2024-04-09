package validator.patron.phone.unique;

import com.library.demo.repository.PatronRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniquePhoneValidator implements ConstraintValidator<UniquePhone,String> {
    @Autowired
    PatronRepository patronRepository;
    @Override
    public boolean isValid(String phone, ConstraintValidatorContext constraintValidatorContext) {
        return !this.patronRepository.existsByPhone(phone);
    }
}
