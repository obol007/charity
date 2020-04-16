package pl.coderslab.charity.DTO;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
public class ResetPasswordDTO {

    Long id;
    @Email(message = "Podaj prawidłowy e-mail")
    private String email;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Hasło musi posiadać co najmniej 8 znaków" +
            " w tym co najmniej jedną wielką literę, małą literę, liczbę i znak specjalny")
    private String password;
    private String rePassword;
    private Boolean blocked;
    private Boolean active;
    private Boolean registered;
}

