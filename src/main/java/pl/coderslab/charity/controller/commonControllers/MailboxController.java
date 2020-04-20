package pl.coderslab.charity.controller.commonControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.DTO.MailboxDTO;
import pl.coderslab.charity.domain.model.Mailbox;
import pl.coderslab.charity.service.MailboxService;

import java.util.List;

@Controller
@RequestMapping("/mailbox")
public class MailboxController {

    MailboxService mailboxService;

    public MailboxController(MailboxService mailboxService) {
        this.mailboxService = mailboxService;
    }

    @GetMapping
    public String mailbox(Model model) {
        List<Mailbox> mails = mailboxService.findAll();
        model.addAttribute("mails", mails);
        return "user_admin/mail/mailbox";
    }

    @GetMapping("/{id}")
    public String readMail(@PathVariable Long id, Model model) {
        MailboxDTO mail = mailboxService.findById(id);
        mailboxService.setOpened(id);
        model.addAttribute("mail", mail);
        return "user_admin/mail/mail";
    }


}
