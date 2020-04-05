package pl.coderslab.charity.DTO;

import lombok.Data;
import pl.coderslab.charity.validation.constraint.UniqueEmail;
import pl.coderslab.charity.validation.validator.AdminValidator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;

@Data
public class UserDTO {

    private Long id;
    @NotBlank(message = "podaj swoje imię")
    private String firstName;
    @NotBlank(message = "podaj swoje nazwisko")
    private String lastName;

    @NotNull(groups = {AdminValidator.class, Default.class})
    @UniqueEmail(message = "Użytkownik o podanym emailu już istnieje", groups = {AdminValidator.class, Default.class})

    private String email;

    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}", message = "Hasło musi posiadać co najmniej 6 znaków" +
            " w tym co najmniej jedną wielką literę i jedną liczbę", groups = {AdminValidator.class, Default.class})
    private String password;
    private String role;
    private Boolean active;


}
