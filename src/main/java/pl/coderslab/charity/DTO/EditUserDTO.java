package pl.coderslab.charity.DTO;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data

public class EditUserDTO {

    Long id;
    @Length(min = 3, message = "Wpisz co najmniej trzy litery")
    String firstName;
    @NotBlank(message = "Wpisz nazwisko")
    String lastName;

    String password;
    String oldPassword;
    @Pattern(regexp = "(?=.*[a-z]).{4,}",message = "Hasło musi mieć co najmniej 4 znaki")
    String newPassword;
    String reNewPassword;

}
