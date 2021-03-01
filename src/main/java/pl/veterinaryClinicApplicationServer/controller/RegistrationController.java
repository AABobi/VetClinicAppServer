package pl.veterinaryClinicApplicationServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;
import pl.veterinaryClinicApplicationServer.model.*;
import pl.veterinaryClinicApplicationServer.repository.AdminsRepository;
import pl.veterinaryClinicApplicationServer.repository.DoctorsRepository;
import pl.veterinaryClinicApplicationServer.repository.PasswordsRepository;
import pl.veterinaryClinicApplicationServer.repository.UsersRepository;
import pl.veterinaryClinicApplicationServer.service.MailService;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping({"/"})
@RestController
public class RegistrationController<T> {

    @Autowired
    public PasswordsRepository passwordsRepository;

    @Autowired
    public UsersRepository usersRepository;

    @Autowired
    private MailService notificationService;

    @Autowired
    public AdminsRepository adminsRepository;

    @Autowired
    public DoctorsRepository doctorsRepository;


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
    /*@PostMapping("/userLogInPath")
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
    }*/


    @PostMapping("/userLogInPath")
    public Users userLogIn(@RequestBody Users users) {
        List<Users> correctUsers = new ArrayList<>(usersRepository.findByNickname(users.getNickname()));
        Users tmpFalseUser = new Users("false", "false");

        if (correctUsers.size() != 0) {
            if (correctUsers.get(0).getPasswords().getPassword().equals(users.getPasswords().getPassword()) &&
                    !correctUsers.get(0).getPermissions().equals("Not confirmed")) {
                users.setId(correctUsers.get(0).getId());
                // users.getPasswords().setPassword(String.valueOf(correctUsers.get(i).getId()));
                System.out.println("true");
                return users;
            } else if (correctUsers.get(0).getPasswords().getPassword().equals(users.getPasswords().getPassword()) &&
                    correctUsers.get(0).getPermissions().equals("Not confirmed")) {
                System.out.println("Not confirmed");
                tmpFalseUser.setName("NC");
                return tmpFalseUser;
            }
        }
        List<Admins> correctAdmins = new ArrayList<>(adminsRepository.findByNickname(users.getNickname()));
        if (correctAdmins.size() == 1) {
            if (correctAdmins.get(0).getPasswords().getPassword().equals(users.getPasswords().getPassword()) &&
                    !correctAdmins.get(0).getPermissions().equals("Not confirmed")) {
                users.setId(correctAdmins.get(0).getId());
                // users.getPasswords().setPassword(String.valueOf(correctUsers.get(i).getId()));
                System.out.println("true");
                return users;
            } else if (correctAdmins.get(0).getPasswords().getPassword().equals(users.getPasswords().getPassword()) &&
                    correctAdmins.get(0).getPermissions().equals("Not confirmed")) {
                System.out.println("Not confirmed");
                tmpFalseUser.setName("NC");
                return tmpFalseUser;
            }
        }
        List<Doctors> correctDoctors = new ArrayList<>(doctorsRepository.findByNickname(users.getNickname()));
        if (correctDoctors.size() == 1) {
            if (correctDoctors.get(0).getPasswords().getPassword().equals(users.getPasswords().getPassword()) &&
                    !correctDoctors.get(0).getPermissions().equals("Not confirmed")) {
                users.setId(correctDoctors.get(0).getId());
                // users.getPasswords().setPassword(String.valueOf(correctUsers.get(i).getId()));
                System.out.println("true");
                return users;
            } else if (correctDoctors.get(0).getPasswords().getPassword().equals(users.getPasswords().getPassword()) &&
                    correctDoctors.get(0).getPermissions().equals("Not confirmed")) {
                System.out.println("Not confirmed");
                tmpFalseUser.setName("NC");
                return tmpFalseUser;
            }
        }
        return null;
    }


    //Create user
    //Password have the same id as user
    @PostMapping(path = "/create")
    public Users createUser(@RequestBody Users users) {
        List<Users> idList = new ArrayList<>(usersRepository.findByNickname(users.getNickname()));
        Users newUsers;
        Passwords newUserPassword;
        //if (idList.size() == 0 && usersRepository.findByEmail(users.getEmail()).size() == 0) {
          if(1==1){
            idList.removeAll(idList);
            newUserPassword = new Passwords(users.getPasswords().getPassword());
              newUsers = new Users(users.getNickname(), users.getName(), users.getLastname(), users.getEmail(),newUserPassword);
              newUsers = new Users(users.getEmail(), users.getName(), users.getLastname(),users.getNickname(),"Not confirmed",newUserPassword);
            usersRepository.save(newUsers);
            notificationService.sendEmail(newUsers);
            return newUsers;
        } else if (usersRepository.findByEmail(users.getEmail()).size() != 0) {
            newUsers = new Users("email");
            return newUsers;
        } else {
            newUsers = new Users("nickname");
            return newUsers;
        }

    }
}