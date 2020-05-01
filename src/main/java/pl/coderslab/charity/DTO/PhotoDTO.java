package pl.coderslab.charity.DTO;


import lombok.Data;
import lombok.ToString;

@Data
public class PhotoDTO {

    private String name;
    private String contentType;

    @ToString.Exclude
    private byte[] content;

    private String email;
}
