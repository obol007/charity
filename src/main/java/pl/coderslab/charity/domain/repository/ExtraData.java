package pl.coderslab.charity.domain.repository;

import lombok.Data;
import pl.coderslab.charity.domain.model.User;


@Data
public class ExtraData {


    private User user;
    private Long bags;
    private Long donations;

    public ExtraData(User user, Long bags, Long donations) {
        this.user = user;
        this.bags = bags;
        this.donations = donations;
    }
}

