package pl.coderslab.charity.service;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.DTO.InstitutionDTO;
import pl.coderslab.charity.domain.model.Institution;
import pl.coderslab.charity.domain.repository.InstitutionRepository;

import java.util.Optional;

@Service
@Transactional
@Slf4j
public class InstitutionService {

    InstitutionRepository institutionRepository;

    public InstitutionService(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    public void addOrUpdate(InstitutionDTO institutionDTO) {
        ModelMapper mapper = new ModelMapper();

        if (institutionDTO.getId()==null) {

            Institution institution = mapper.map(institutionDTO, Institution.class);
            institutionRepository.save(institution);

        } else {
            log.warn("JESTEM W ELSE");
            Optional<Institution> optInstitution = institutionRepository.findById(institutionDTO.getId());
            if(optInstitution.isPresent()) {
                Institution institutionOriginal = optInstitution.get();
                mapper.map(institutionDTO, institutionOriginal);
                institutionRepository.save(institutionOriginal);
            }
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
