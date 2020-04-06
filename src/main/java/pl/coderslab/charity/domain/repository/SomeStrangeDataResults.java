package pl.coderslab.charity.domain.repository;

import lombok.Data;

@Data
public class SomeStrangeDataResults {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Long count;

    public SomeStrangeDataResults() {
    }

    public SomeStrangeDataResults(Long id, String email, String firstName, String lastName, Long count) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.count = count;
    }
}