package pl.coderslab.charity.DTO;

import lombok.Data;
import pl.coderslab.charity.validation.constraint.UniqueEmail;
import pl.coderslab.charity.validation.validator.AdminValidator;

import javax.validation.constraints.NotBlank;
import javax.validation.groups.Default;

@Data
public class NewAdminDTO {

    private Long id;
    @NotBlank(message = "Wpisz imię")
    private String firstName;
    @NotBlank(message = "Wpisz nazwisko")
    private String lastName;

    @NotBlank(message = "Wpisz email",groups = {AdminValidator.class, Default.class})
    @UniqueEmail(message = "Użytkownik o podanym emailu już istnieje", groups = {AdminValidator.class, Default.class})
    private String email;

    @NotBlank(message = "Podaj hasło", groups = {AdminValidator.class, Default.class})
    private String password;

    private String rePassword;

}
