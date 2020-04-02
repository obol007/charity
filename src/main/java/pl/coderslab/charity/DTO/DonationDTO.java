package pl.coderslab.charity.DTO;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.charity.domain.model.Category;
import pl.coderslab.charity.domain.model.Institution;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class DonationDTO {

    private Long id;
    @Min(1)
    private Integer quantity;
    private String street;
    private String city;
    private String zipCode;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;

    private LocalTime pickUpTime;
    private String pickUpComment;

    private List<Category> categories;

    private Institution institution;

}
