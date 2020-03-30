package pl.coderslab.charity.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter @Setter @EqualsAndHashCode(callSuper = false) @ToString
public class Category extends Base {

    private String name;






}
