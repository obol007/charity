package pl.coderslab.charity.DTO;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UserDTO {

    private Long id;
    @NotBlank(message = "podaj swoje imię")
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    @Email
    private String email;

    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}", message = "Hasło musi posiadać co najmniej 6 znaków" +
            " w tym co najmniej jedną wielką literę i jedną liczbę")
    private String password;
    private String role;
    private Boolean active;


}
