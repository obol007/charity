package pl.coderslab.charity.DTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class InstitutionDTO {

    private Long id;
    @NotBlank(message = "Podaj nazwę fundacji")
    private String name;
    @NotBlank(message = "Podaj opis fundacji")
    private String description;
    private Boolean active;

}
