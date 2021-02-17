package pl.veterinaryClinicApplicationServer.service;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import pl.veterinaryClinicApplicationServer.model.Users;
import pl.veterinaryClinicApplicationServer.model.UsersRepository;

import java.util.ArrayList;
import java.util.List;


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