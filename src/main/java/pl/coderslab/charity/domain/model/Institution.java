package pl.coderslab.charity.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Getter @Setter @ToString @EqualsAndHashCode(callSuper = true)
public class Institution extends Base {

    private String name;
    private String description;
    private Boolean active = true;

}
