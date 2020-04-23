package pl.coderslab.charity.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.DTO.MailboxDTO;
import pl.coderslab.charity.domain.model.Mailbox;
import pl.coderslab.charity.domain.repository.MailboxRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MailboxService {

    MailboxRepository mailboxRepository;

    public MailboxService(MailboxRepository mailboxRepository) {
        this.mailboxRepository = mailboxRepository;
    }

    public List<Mailbox> findAll() {
        return mailboxRepository.findTop10ByOrderByCreatedDesc();
    }

    public void send(String email, String message, String title) {
        Mailbox mailbox = new Mailbox();
        mailbox.setRecipient(email);
        mailbox.setTitle(title);
        mailbox.setContent(message);
        mailboxRepository.save(mailbox);
    }

    public MailboxDTO findById(Long id) {
        Optional<Mailbox> mailboxOpt = mailboxRepository.findById(id);
        ModelMapper mapper = new ModelMapper();
        MailboxDTO mailboxDTO = new MailboxDTO();
        if(mailboxOpt.isPresent()){
            mailboxDTO = mapper.map(mailboxOpt.get(),MailboxDTO.class);
        }
        return mailboxDTO;
    }

    public void setOpened(Long id) {
       Optional<Mailbox> mail =  mailboxRepository.findById(id);
        mail.ifPresent(mailbox -> mailbox.setOpened(true));
        mail.ifPresent(mailbox -> mailboxRepository.save(mailbox));

    }
}
