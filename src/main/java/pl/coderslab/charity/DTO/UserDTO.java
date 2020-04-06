package pl.coderslab.charity.DTO;

import lombok.Data;
import org.hibernate.validator.constraints.pl.REGON;
import pl.coderslab.charity.validation.constraint.RetypedPassword;
import pl.coderslab.charity.validation.constraint.UniqueEmail;
import pl.coderslab.charity.validation.validator.AdminValidator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;

@Data
//@RetypedPassword(message = "Hasła się nie zgadzają")
public class UserDTO {


    private Long id;
    @NotBlank(message = "Wpisz imię")
    private String firstName;
    @NotBlank(message = "Wpisz nazwisko")
    private String lastName;

    @NotBlank(groups = {AdminValidator.class, Default.class},message = "Wpisz email")
    @UniqueEmail(message = "Użytkownik o podanym emailu już istnieje", groups = {AdminValidator.class, Default.class})
    private String email;

    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}", message = "Hasło musi posiadać co najmniej 6 znaków" +
            " w tym co najmniej jedną wielką literę i jedną liczbę", groups = {AdminValidator.class, Default.class})
    private String password;


    private String rePassword;
    private String role;
    private Boolean active;




}
