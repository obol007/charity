package pl.coderslab.charity.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.domain.model.Mailbox;

import java.time.LocalDateTime;
import java.util.List;

public interface MailboxRepository extends JpaRepository <Mailbox, Long> {

    //    @Query("select m from Mailbox m order by m.created desc ")
    List<Mailbox> findTop10ByOrderByCreatedDesc();


}