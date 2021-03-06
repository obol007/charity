package pl.coderslab.charity.service;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pl.coderslab.charity.DTO.InstitutionDTO;
import pl.coderslab.charity.domain.model.Institution;
import pl.coderslab.charity.domain.repository.InstitutionRepository;

import java.util.ArrayList;
import java.util.List;
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
            Optional<Institution> optInstitution = institutionRepository.findById(institutionDTO.getId());
            if(optInstitution.isPresent()) {
                Institution institutionOriginal = optInstitution.get();
                Institution institution = mapper.map(institutionDTO, Institution.class);
                institution.setId(institutionOriginal.getId());
//                mapper.map(institutionDTO, institutionOriginal);
//                institutionRepository.save(institutionOriginal);
                institutionRepository.save(institution);


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

    public List<InstitutionDTO> findAllOderByIdDesc() {
        ModelMapper mapper = new ModelMapper();
        List<Institution> institutions = institutionRepository.findAllOderByIdDesc();
        List<InstitutionDTO> institutionDTOS = new ArrayList<>();
        for (Institution i : institutions) {
            institutionDTOS.add(mapper.map(i, InstitutionDTO.class));
        }
        return institutionDTOS;
    }

    public InstitutionDTO findById(Long id) {
        Institution institution = institutionRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        ModelMapper mapper = new ModelMapper();
        return mapper.map(institution, InstitutionDTO.class);
    }

    public void save(InstitutionDTO institutionDTO) {
        ModelMapper mapper = new ModelMapper();
        institutionRepository.save(mapper.map(institutionDTO,Institution.class));
    }


    public List<InstitutionDTO> findAllByActiveDTO(boolean b) {
        ModelMapper mapper = new ModelMapper();
        List<Institution> institutions = institutionRepository.findAllByActive(b);
        List<InstitutionDTO> institutionDTOS = new ArrayList<>();
        for (Institution i : institutions) {
            institutionDTOS.add(mapper.map(i, InstitutionDTO.class));
        }
        return institutionDTOS;
    }
    public List<Institution> findAllByActive(){
        return institutionRepository.findAllByActive(true);
    }

    public void changeActive(Long id) {
        Optional<Institution> institutionOptional = institutionRepository.findById(id);
        if(institutionOptional.isPresent()) {
            Institution institution = institutionOptional.get();
            institution.setActive(!institution.getActive());
            institutionRepository.save(institution);
        }

    }
}
