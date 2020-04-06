package pl.coderslab.charity.DTO;


import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class AdminDTO {

    Long id;
    @NotBlank
    String firstName;
    String lastName;


}
