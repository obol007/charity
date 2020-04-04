package pl.coderslab.charity.validation.validator;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.domain.repository.UserRepository;
import pl.coderslab.charity.validation.constraint.UniqueEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@Scope("prototype")
//walidator jest dla Stringów <...,String>
public class UniqueEmailStringValidator implements ConstraintValidator<UniqueEmail, String> {

    public final UserRepository userRepository;

    public UniqueEmailStringValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void initialize(UniqueEmail constraint) {
    }

    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null) {
            return true;
        }

        return !userRepository.existsByEmail(email);
    }
}
