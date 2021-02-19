package pl.veterinaryClinicApplicationServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;
import pl.veterinaryClinicApplicationServer.repository.PasswordsRepository;
import pl.veterinaryClinicApplicationServer.model.Users;
import pl.veterinaryClinicApplicationServer.repository.UsersRepository;
import pl.veterinaryClinicApplicationServer.service.MailService;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping({"/"})
@RestController
public class RegistrationController {

    @Autowired
    public PasswordsRepository passwordsRepository;

    @Autowired
    public UsersRepository usersRepository;

    @Autowired
    private MailService notificationService;




    @RequestMapping("send-mail")
    public String send(Users users) {

      /*  user.setFirstName("Radosław");
        user.setLastName("Kopeć");
        user.setEmailAddress("radoslawkopec93@gmail.com"); //Receiver's email address*/

        try {
            notificationService.sendEmail(users);
        } catch (MailException mailException) {
            System.out.println(mailException);
        }
        return "Congratulations! Your mail has been send to the user.";
    }

    public void send2(Users users) {

      /*  user.setFirstName("Radosław");
        user.setLastName("Kopeć");
        user.setEmailAddress("radoslawkopec93@gmail.com"); //Receiver's email address*/

        try {
            notificationService.sendEmail(users);
            System.out.println("ggg");
        } catch (MailException mailException) {
            System.out.println(mailException);
        }
        System.out.println("gg");
    }


    //Method gets an object(name,last name, password). Then method findByNameAndLastname looks for users with matching values
    // and try to match password to user. After correct match method sends an instance of the users class to the client.
    @PostMapping("/userLogInPath")
    public Users userLogIn(@RequestBody Users users) {
        List<Users> correctUsers = new ArrayList<>(usersRepository.findByNickname(users.getNickname()));
        Users tmpFalseUser = new Users("false", "false");
        System.out.println(correctUsers.size());
        for (int i = 0; i < correctUsers.size(); i++) {
            //&& correctUsers.get(i).getPasswords().getConfirmed()==1
            if (correctUsers.get(i).getPasswords().getPassword().equals(users.getPasswords().getPassword())) {
                users.setId(correctUsers.get(i).getId());
                // users.getPasswords().setPassword(String.valueOf(correctUsers.get(i).getId()));
                System.out.println("true");
                return users;
            } else if (correctUsers.get(i).getPasswords().getPassword().equals(users.getPasswords().getPassword())){
                System.out.println("Not confirmed");
                tmpFalseUser.setName("NC");
                return tmpFalseUser;
            }else {
                System.out.println("False");
            }
        }
        System.out.println("false");
        return tmpFalseUser;
    }

    //Create user
    //Password have the same id as user
    @PostMapping(path = "/create")
    public Users createUser(@RequestBody Users users) {
        List<Users> idList = new ArrayList<>(usersRepository.findByNickname(users.getNickname()));
        Users newUsers, newUsersSecond;
        if (idList.size() == 0 && usersRepository.findByEmail(users.getEmail()).size() == 0) {
            idList.removeAll(idList);
            users.getPasswords().setId(1);
            newUsers = new Users(users.getNickname(), users.getName(), users.getLastname(), users.getEmail());
            usersRepository.save(newUsers);
            idList = usersRepository.findByNickname(newUsers.getNickname());
            users.getPasswords().setId(idList.get(0).getId());
            newUsers.setPasswords(users.getPasswords());
            usersRepository.save(newUsers);
            return newUsers;
        } else if(usersRepository.findByEmail(users.getEmail()).size() != 0){
            newUsers = new Users("email");
            return newUsers;
        }else {
            newUsers = new Users("nickname");
            return newUsers;
        }

    }
}