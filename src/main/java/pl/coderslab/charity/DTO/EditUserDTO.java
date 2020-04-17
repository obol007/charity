package pl.coderslab.charity.DTO;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import pl.coderslab.charity.validation.validator.AdminValidator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;

@Data

public class EditUserDTO {

    Long id;
    @Length(min = 3, message = "Wpisz co najmniej trzy litery")
    String firstName;
    @NotBlank(message = "Wpisz nazwisko")
    String lastName;

    String password;
    String oldPassword;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Hasło musi posiadać co najmniej 8 znaków" +
            " w tym co najmniej jedną wielką literę, małą literę, liczbę i znak specjalny", groups = {AdminValidator.class, Default.class})
    String newPassword;
    String reNewPassword;

    public String getFullName(){
        return firstName+" "+lastName;
    }

}
