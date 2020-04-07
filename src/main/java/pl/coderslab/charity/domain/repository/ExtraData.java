package pl.coderslab.charity.domain.repository;

import lombok.Data;

@Data
public class ExtraData {


    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Long donations;
    private Long bags;

    public ExtraData() {
    }

    public ExtraData(Long id, String email, String firstName, String lastName, Long donations, Long bags) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.donations = donations;
        this.bags = bags;
    }
    String getFullName(){
        return firstName+" "+lastName;
    }


}

