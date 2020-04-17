package pl.coderslab.charity.DTO;

import lombok.Data;

@Data
public class InstitutionDTO {

    private Long id;
    private String name;
    private String description;
    private Boolean active;

}
