package pl.coderslab.charity.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.DTO.PhotoDTO;
import pl.coderslab.charity.domain.model.Photo;
import pl.coderslab.charity.domain.model.User;
import pl.coderslab.charity.domain.repository.PhotoRepository;
import pl.coderslab.charity.domain.repository.UserRepository;

import java.util.Optional;

@Service
@Transactional
public class PhotoService {

    PhotoRepository photoRepository;
    UserRepository userRepository;

    public PhotoService(PhotoRepository photoRepository, UserRepository userRepository) {
        this.photoRepository = photoRepository;
        this.userRepository = userRepository;
    }

    public void save(PhotoDTO photoDTO) {

        if (photoDTO.getName() != null && photoDTO.getContent() != null) {

            User user = userRepository.findByEmail(photoDTO.getEmail());
            Optional<Photo> photoOptional = photoRepository.findByUserId(user.getId());
            Photo photo;
            if(photoOptional.isEmpty()){
                photo = new Photo();
            }else {
                photo = photoOptional.get();
            }
            photo.setContent(photoDTO.getContent());
            photo.setContentType(photoDTO.getContentType());
            photo.setName(photoDTO.getName());
            photo.setUser(user);
            photoRepository.save(photo);
        }
    }

    public Optional<Photo> getPhoto(String email) {
        User user = userRepository.findByEmail(email);
        Optional<Photo> photo = photoRepository.findByUserId(user.getId());
        if(photo.isEmpty()){
            return Optional.empty();
        }
        return photo;
    }

    public Boolean hasPhoto(String email) {
        User user = userRepository.findByEmail(email);
        Optional<Photo> photo = photoRepository.findByUserId(user.getId());
        return photo.isPresent();
    }
}
