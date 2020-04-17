package pl.coderslab.charity.validation.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.validation.constraint.DonationTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalTime;

@Component
@Scope("prototype")
@Slf4j
public class DonationTimeValidator implements ConstraintValidator<DonationTime, LocalTime> {

    @Override
    public void initialize(DonationTime constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalTime localTime, ConstraintValidatorContext constraintValidatorContext) {

        if(localTime == null){
            return true;
        }
        return !(localTime.isBefore(LocalTime.of(9, 00))
                || localTime.isAfter(LocalTime.of(18,0)));
    }
}
