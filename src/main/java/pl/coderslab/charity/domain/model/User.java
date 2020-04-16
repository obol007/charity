package pl.coderslab.charity.domain.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity @Getter @Setter @ToString(exclude = "password") @EqualsAndHashCode(callSuper = true)
@Table(name = "users")

public class User extends Base {


    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String role;
    private Boolean active = false;
    private Boolean blocked = false;
    private Boolean registered = false;


    public String getFullName(){
        return firstName+" "+lastName;
    }

}
