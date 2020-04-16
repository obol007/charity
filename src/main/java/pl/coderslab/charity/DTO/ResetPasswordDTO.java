package pl.coderslab.charity.DTO;

import lombok.Data;
import pl.coderslab.charity.validation.validator.AdminValidator;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;

@Data
public class ResetPasswordDTO {

    @Email
    private String email;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Hasło musi posiadać co najmniej 8 znaków" +
            " w tym co najmniej jedną wielką literę, małą literę, liczbę i znak specjalny")
    private String password;
    private String rePassword;
    private Boolean blocked;
    private Boolean active;
}
