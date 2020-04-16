package pl.coderslab.charity.DTO;

import lombok.Data;
import org.springframework.security.core.parameters.P;
import pl.coderslab.charity.validation.constraint.UniqueEmail;
import pl.coderslab.charity.validation.validator.AdminValidator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;

@Data
public class UserDTO {


    private Long id;
    @NotBlank(message = "Wpisz imię")
    @Pattern(regexp = "^([A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ)]){3,10}$",message = "Imię musi miec min 3 i max 10 liter")
    private String firstName;
    @NotBlank(message = "Wpisz nazwisko")
    @Pattern(regexp = "^[A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]{3,20}$",message = "Nazwisko musi miec min 3 i max 20 liter")
    private String lastName;

    @NotBlank(groups = {AdminValidator.class, Default.class},message = "Wpisz email")
    @UniqueEmail(message = "Użytkownik o podanym emailu już istnieje", groups = {AdminValidator.class, Default.class})
    @Pattern(regexp = "[_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.([a-zA-Z]{2,}){1}",
            message = "Wpisz poprawny e-mail!")
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
