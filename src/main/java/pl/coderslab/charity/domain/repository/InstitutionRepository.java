package pl.coderslab.charity.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.domain.model.Institution;

import java.util.List;

public interface InstitutionRepository extends JpaRepository<Institution, Long> {

List<Institution> findAllByActive(Boolean test);
}
