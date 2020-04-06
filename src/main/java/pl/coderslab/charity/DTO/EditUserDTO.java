package pl.coderslab.charity.DTO;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data

public class EditUserDTO {

    Long id;
    @Length(min = 3, message = "Wpisz co najmniej trzy litery")
    String firstName;
    @NotBlank(message = "Wpisz nazwisko")
    String lastName;

    String password;
    String newPassword;
    String oldPassword;

}
