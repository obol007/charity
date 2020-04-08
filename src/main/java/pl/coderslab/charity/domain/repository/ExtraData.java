package pl.coderslab.charity.domain.repository;

import lombok.Data;
import pl.coderslab.charity.domain.model.User;


@Data
public class ExtraData {


    private User users;
    private Long bags;
    private Long donations;

    public ExtraData(User users, Long bags, Long donations) {
        this.users = users;
        this.bags = bags;
        this.donations = donations;
    }
}

