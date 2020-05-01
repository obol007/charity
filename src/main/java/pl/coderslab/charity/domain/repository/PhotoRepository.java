package pl.coderslab.charity.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.domain.model.Photo;

import java.util.Optional;

public interface PhotoRepository extends JpaRepository<Photo, Long> {





    Optional<Photo> findByUserId(Long id);
}
