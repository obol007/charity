package pl.coderslab.charity.controller.commonControllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.charity.DTO.PhotoDTO;
import pl.coderslab.charity.DTO.UserDTO;
import pl.coderslab.charity.domain.model.Photo;
import pl.coderslab.charity.service.PhotoService;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/upload")
@Slf4j
public class PhotoController {

    PhotoService photoService;
    UserService userService;

    public PhotoController(PhotoService photoService, UserService userService) {
        this.photoService = photoService;
        this.userService = userService;
    }

    @ModelAttribute("loggedUser")
    public UserDTO loggedUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Boolean hasPhoto = photoService.hasPhoto(email);
        UserDTO userDTO = userService.findUserDTOByEmail(email);
        if(hasPhoto){
            userDTO.setHasImage(true);
        }
        return userDTO;
    }

    @GetMapping
    public String userPage(){
        return "redirect:/user";
    }

    @PostMapping
    public String uploadPhoto(@RequestParam MultipartFile photo, @Valid @ModelAttribute PhotoDTO photoDTO, BindingResult result) {

        try {
            String name = photo.getOriginalFilename();
            String contentType = photo.getContentType();
            byte[] content = photo.getBytes();

            String email = SecurityContextHolder.getContext().getAuthentication().getName();

            photoDTO.setName(name);
            photoDTO.setContentType(contentType);
            photoDTO.setContent(content);
            photoDTO.setEmail(email);

            photoService.save(photoDTO);


        } catch (IOException ioe) {
            log.error("Błąd przetwarzania pliku", ioe);
            result.rejectValue("photo", null, "Błąd przetwarzania pliku");
        }

        return "user/user";
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> getPhoto(@PathVariable("email") String email) {
        Optional<Photo> optionalPhoto = photoService.getPhoto(email);
        if (optionalPhoto.isPresent()) {
            Photo photo = optionalPhoto.get();
            ByteArrayResource resource = new ByteArrayResource(photo.getContent());

            return ResponseEntity
                    .ok()
                    .contentType(MediaType.valueOf(photo.getContentType()))
                    .header("Content-Disposition", "filename=" + photo.getName())
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }

    }
}
