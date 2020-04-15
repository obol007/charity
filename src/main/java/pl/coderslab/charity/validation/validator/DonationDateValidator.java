package pl.coderslab.charity.validation.validator;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.validation.constraint.DonationDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

@Component
@Scope("prototype")
public class DonationDateValidator implements ConstraintValidator<DonationDate, LocalDate> {
    @Override
    public void initialize(DonationDate constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext constraintValidatorContext) {

        return date.isAfter(LocalDate.now().plusDays(1));
    }
}
