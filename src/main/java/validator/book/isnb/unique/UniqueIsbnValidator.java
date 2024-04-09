package validator.book.isnb.unique;

import com.library.demo.repository.BookRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueIsbnValidator implements ConstraintValidator<UniqueIsbn, String> {
    @Autowired
    BookRepository bookRepository;

    @Override
    public boolean isValid(String isbn, ConstraintValidatorContext constraintValidatorContext) {
        return !this.bookRepository.existsByIsbn(isbn);
    }
}
