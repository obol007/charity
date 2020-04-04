package pl.coderslab.charity.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.DTO.InstitutionDTO;
import pl.coderslab.charity.domain.model.Institution;
import pl.coderslab.charity.domain.repository.InstitutionRepository;

import java.util.Optional;

@Service
@Transactional
public class InstitutionService {

    InstitutionRepository institutionRepository;

    public InstitutionService(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    public void addOrUpdate(InstitutionDTO institutionDTO) {
        ModelMapper mapper = new ModelMapper();
        Optional<Institution> optInstitution = institutionRepository.findById(institutionDTO.getId());
        //puste, wiec zapisujemy nowa, z id institutionDTO
        if (optInstitution.isEmpty()) {
            Institution institution = mapper.map(institutionDTO, Institution.class);
            institutionRepository.save(institution);
            //istnieje, wiec uaktualniamy, z id instytucji
        } else {
                Institution institutionOryginal = optInstitution.get();
                Institution institution = mapper.map(institutionDTO, Institution.class);
                institution.setId(institutionOryginal.getId());
                institutionRepository.save(institution);
        }
    }


    public InstitutionDTO findInstitution(Long id) {
        ModelMapper mapper = new ModelMapper();
        Optional<Institution> optInstitution = institutionRepository.findById(id);
        if (optInstitution.isPresent()) {
            return mapper.map(optInstitution.get(), InstitutionDTO.class);
        }
        return new InstitutionDTO();
    }

    public void delete(Long id) {
        institutionRepository.deleteById(id);
    }
}
