package pl.coderslab.charity.DTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class EmailDTO {
    @NotBlank(message = "Podaj email")
    private String email;
}
