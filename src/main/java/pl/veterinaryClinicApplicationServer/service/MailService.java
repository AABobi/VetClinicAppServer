package pl.veterinaryClinicApplicationServer.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pl.veterinaryClinicApplicationServer.model.Users;
import pl.veterinaryClinicApplicationServer.repository.UsersRepository;


@Service
public class MailService {


    private JavaMailSender javaMailSender;

    @Autowired
    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Autowired
    public UsersRepository usersRepository;
    public void sendEmail(Users users) throws MailException {

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo(users.getEmail());
        mail.setSubject("Przychodnia weterynaryjna");
        String mainUrl = "http://localhost:4200/UserLogInComponent/"+String.valueOf(users.getId());
        mail.setText(mainUrl);

        javaMailSender.send(mail);
    }



}