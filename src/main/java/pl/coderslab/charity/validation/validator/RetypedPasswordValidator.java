package pl.coderslab.charity.validation.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.DTO.UserDTO;
import pl.coderslab.charity.validation.constraint.RetypedPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
@Component
@Scope("prototype")
@Slf4j
public class RetypedPasswordValidator implements ConstraintValidator <RetypedPassword, Object> {

    @Override
    public void initialize(RetypedPassword constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        UserDTO userDTO = (UserDTO) o;
        return userDTO.getPassword().equals(userDTO.getRePassword());
    }





}

