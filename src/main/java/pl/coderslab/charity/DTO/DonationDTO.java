package pl.coderslab.charity.DTO;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.charity.domain.model.Category;
import pl.coderslab.charity.domain.model.Institution;
import pl.coderslab.charity.domain.model.User;
import pl.coderslab.charity.validation.constraint.DonationDate;
import pl.coderslab.charity.validation.constraint.DonationTime;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
public class DonationDTO {


    private Long id;

    @NotNull(message = "Podaj liczbę worków")
    private Integer quantity;

    @NotBlank(message = "Podaj nazwę ulicy")
    private String street;

    @NotBlank
    private String city;

    @Pattern(regexp = "[0-9]{2}\\-[0-9]{3}", message = "Podaj kod pocztowy w formacie: xx-xxx")
    private String zipCode;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    @DonationDate
    private LocalDate pickUpDate;


    @NotNull
    @DonationTime
    private LocalTime pickUpTime;
    private String pickUpComment;

    @NotEmpty(message = "Zaznacz przynajmniej jedną kategorię")
    private List<Category> categories;

    @NotNull(message = "Wybierz instytucję")
    private Institution institution;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private LocalDateTime createdOn;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private LocalDateTime collectedDate;
    private Boolean collected = false;

    private User user;
}
