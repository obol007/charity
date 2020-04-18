package pl.coderslab.charity.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.domain.model.Mailbox;

public interface MailboxRepository extends JpaRepository <Mailbox, Long> {
}
