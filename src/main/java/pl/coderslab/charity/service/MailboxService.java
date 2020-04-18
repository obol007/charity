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
        return mailboxRepository.findAll();
    }

    public void send(String email, String message) {
        Mailbox mailbox = new Mailbox();
        mailbox.setRecipient(email);
        mailbox.setTitle("Registration link");
        mailbox.setText(message);
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
}
