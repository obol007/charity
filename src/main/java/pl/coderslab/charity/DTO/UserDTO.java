package pl.coderslab.charity.DTO;

import lombok.Data;
import pl.coderslab.charity.validation.constraint.UniqueEmail;
import pl.coderslab.charity.validation.validator.AdminValidator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;

@Data
public class UserDTO {


    private Long id;
    @NotBlank(message = "Wpisz imię")
    private String firstName;
    @NotBlank(message = "Wpisz nazwisko")
    private String lastName;

    @NotBlank(groups = {AdminValidator.class, Default.class},message = "Wpisz email")
    @UniqueEmail(message = "Użytkownik o podanym emailu już istnieje", groups = {AdminValidator.class, Default.class})
    private String email;

//        (?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Hasło musi posiadać co najmniej 8 znaków" +
            " w tym co najmniej jedną wielką literę, małą literę, liczbę i znak specjalny", groups = {AdminValidator.class, Default.class})
    private String password;


    private String rePassword;
    private String oldPassword;
    private String tryOldPassword;
    private String role;
    private Boolean blocked;


    public String getFullName(){
        return firstName+" "+lastName;
    }




}
