package pl.coderslab.charity.DTO;

import lombok.Data;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.domain.repository.InstitutionRepository;

@Data
@Transactional
public class InstitutionDTO {

    InstitutionRepository institutionRepository;

    public InstitutionDTO(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    private Long id;
    private String name;
    private String description;
    private Boolean active;

}
