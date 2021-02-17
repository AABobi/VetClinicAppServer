package pl.veterinaryClinicApplicationServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.veterinaryClinicApplicationServer.model.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping({"/"})
@RestController
public class UsersController {
    @Autowired
    public UsersRepository usersRepository;
    @Autowired
    public PasswordsRepository passwordsRepository;
    @Autowired
    public DateOfTheVisitRepository dateOfTheVisitRepository;


    @GetMapping("/termsTest")
    public void getDates(Passwords passwords) {
        System.out.println(passwords);
    }

    //This method returns all Ppsswords from database.
    @GetMapping("/findAllP")
    public List<Passwords> getAllPasswords() {
        List<Passwords> passportList = new ArrayList<>();
        Iterable<Passwords> passport = passwordsRepository.findAll();

        for (Passwords p : passport) {
            passportList.add(p);
        }
        return passportList;
    }

    //This method returns all users from database.
    @GetMapping("/findAllMain")
    public List<Users> getAllUsers() {
        List<Users> passportList = new ArrayList<>();
        Iterable<Users> passport = usersRepository.findAll();

        for (Users p : passport) {
            passportList.add(p);
        }

        return passportList;
    }

    /* @GetMapping("/findAllMain1/{id}")
     public Users getAllPassport1(@PathVariable(value = "id") String id) {
         List<Users> passportList = new ArrayList<>();
         Iterable<Users> passport = usersRepository.findAll();

         for (Users p : passport) {
             passportList.add(p);
         }

         return passportList.get(Integer.parseInt(id));
     }
 */
/*
    @PostMapping("/findUser2")
    public void checkPassword(@RequestBody Users users) {
        List<Users> idList = new ArrayList<>();
        idList = usersRepository.findByNickname(users.getNickname());

    }*/
/*
    @PostMapping("/findUser")
    public Users checkPassword3(@RequestBody Users users) {


        List<Users> usersList = new ArrayList<>(usersRepository.findByNameAndLastname(users.getName(), users.getLastname()));
        System.out.println(usersList.size());
        if (usersList.size() == 0) {
            Users falseUser = new Users(0, "false", "false", "false", users.getPasswords(), users.getDateOfTheVisit());
            return falseUser;
        }
        //List<Passwords> passwordsList = new ArrayList<>(passwordsRepository.findByPassword(users));

        return users;
    }*/

    //This method takes object users with date and save it in database.
    @PutMapping("/addTerms")
    public void addTerms(@RequestBody DateOfTheVisit dateOfTheVisit) {

        dateOfTheVisitRepository.save(dateOfTheVisit);
    }


    //Compares next days with "DateOfTheVisit" DB and deletes reserved terms from free terms array(rewrittenDatesV2).
    //Return array with free terms
    @GetMapping("/getTerms")
    public String[] getVisitTerms() {
        DateOfTheVisit dateOfInstance = new DateOfTheVisit();
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd/HHmm");
        Date date = calendar.getTime();
        String[] dates = new String[7];
        boolean nextMouth = false;
        System.out.println(calendar.getTime());

        for (int i = 0; i < dates.length; i++) {
            if ((calendar.getActualMaximum(Calendar.DAY_OF_MONTH) != calendar.DATE) && (nextMouth == false)) {
                calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE) + 1);
                dates = dateOfInstance.isWeekend(calendar, dates, i);
            } else if ((calendar.getActualMaximum(Calendar.DAY_OF_MONTH) == calendar.DATE) && (calendar.getActualMaximum(Calendar.MONTH)) == 11) {
                calendar.set(calendar.get(Calendar.YEAR) + 1, calendar.get(Calendar.MONTH) + 1, 1);
                calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                dates = dateOfInstance.isWeekend(calendar, dates, i);
            } else if ((calendar.getActualMaximum(Calendar.DAY_OF_MONTH) == calendar.DATE) && (calendar.getActualMaximum(Calendar.MONTH)) != 11) {
                calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, 1);
                calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                dates = dateOfInstance.isWeekend(calendar, dates, i);
            }
        }
        String[] termsTimes = {"8:00", "8:30", "9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30"};
        String[] rewrittenDates = dateOfInstance.rewriteArray(dates, termsTimes);
        int reservedTerms = 0;

        for (int i = 0; i < rewrittenDates.length; i++) {
            if (dateOfTheVisitRepository.findByDateof(rewrittenDates[i]).size() != 0) {
                reservedTerms++;
            }
        }
        String[] rewrittenDatesV2 = new String[rewrittenDates.length - reservedTerms];
        reservedTerms = 0;
        for (int i = 0; i < rewrittenDates.length; i++) {
            if (dateOfTheVisitRepository.findByDateof(rewrittenDates[i]).size() != 0) {
                reservedTerms++;
            } else {
                rewrittenDatesV2[i - reservedTerms] = rewrittenDates[i];
            }
        }
        System.out.println(rewrittenDatesV2[2]);
        return rewrittenDatesV2;
    }

/*
    @PostMapping("/test")
    public void testDate(@RequestBody String[] body) {
        List<Users> idList = new ArrayList<>(usersRepository.findByNickname(body[0]));
        if (idList.size() > 0) {
            // String[] parts = body[1].split("T");
            StringBuilder date = new StringBuilder(body[1]);
            date.setCharAt(10, ' ');
            DateOfTheVisit ddo = new DateOfTheVisit(idList.get(0).getId(), date.toString());
            idList.get(0).setDateOfTheVisit(ddo);
            usersRepository.save(idList.get(0));
        }
    }*/

    //Method gets an object(name,last name, password). Then method findByNameAndLastname looks for users with matching values
    // and try to match password to user. After correct match method sends an instance of the users class to the client.
   /* @PostMapping("/userLogInPath")
    public Users userLogIn(@RequestBody Users users) {
        System.out.println("1");
        List<Users> correctUsers = new ArrayList<>(usersRepository.findByNickname(users.getNickname()));
        System.out.println("2");
        Users tmpFalseUser = new Users("false", "false");
        System.out.println("3");
        System.out.println(correctUsers.size());
        for (int i = 0; i < correctUsers.size(); i++) {
            if (correctUsers.get(i).getPasswords().getPassword().equals(users.getPasswords().getPassword())) {
                users.setId(correctUsers.get(i).getId());
                // users.getPasswords().setPassword(String.valueOf(correctUsers.get(i).getId()));
                System.out.println("true");
                return users;
            } else {
                System.out.println("falseif");
                return tmpFalseUser;
            }
        }
        System.out.println("false");
        return tmpFalseUser;
    }*/

    //przysylamy id uzytkownika po zalogowaniu
    @GetMapping("/confirm/{id}")
    public Users userAccout(@PathVariable(value = "id") int id) {
        System.out.println(id);
        List<Users> tmp = new ArrayList<>(usersRepository.findById(id));

        return tmp.get(0);
    }

    //This method confirms user account
    @GetMapping("/contest/{id}")
    public void  conTest(@PathVariable(value = "id") int id){
        List<Users> findUser = new ArrayList<>(usersRepository.findById(id));
        if(findUser.size() > 0){
            findUser.get(0).getPasswords().setConfirmed(1);
            usersRepository.save(findUser.get(0));
        }
    }

    @GetMapping("/findUser/{userName}")
    public Users findUser(@PathVariable(value =  "userName") String userName){
        List<Users> findUser = new ArrayList<>(usersRepository.findByNickname(userName));
        if(findUser.size() == 1) {
            return findUser.get(0);
        }else {
            Users tmpUser = new Users("no found", "no found");
            return tmpUser;
        }
    }

    @PostMapping("/findUserForAdmin")
    public Users[] findUserForAdmin(@RequestBody Users users){
        List<Users> findUserForAdmin = new ArrayList<>(usersRepository.findByNameAndLastname(users.getName(),users.getLastname()));

        if(findUserForAdmin.size() != 0){
            Users[] usersArray = new Users[findUserForAdmin.size() ];
            for(int i=0;i < findUserForAdmin.size();i++ ){
                usersArray[i] = findUserForAdmin.get(i);
            }
            return usersArray;
        }

        return null;
    }

    // Create user
    // First : creates user with password id 1
    // Then saves user and makes password id equals user id
    // Delete passsword id 1
  /*  @PostMapping(path = "/create")
    public Users createUser(@RequestBody Users users) {
        List<Users> idList = new ArrayList<>(usersRepository.findByNickname(users.getNickname()));
        Users newUsers;
        if (idList.size() == 0) {
            idList.removeAll(idList);
            users.getPasswords().setId(1);
            newUsers = new Users(users.getNickname(), users.getName(), users.getLastname(), users.getEmail(), users.getPasswords());
            usersRepository.save(newUsers);
            idList = usersRepository.findByNickname(newUsers.getNickname());
            Passwords newPassword = new Passwords(idList.get(0).getId(), newUsers.getPasswords().getPassword());
            passwordsRepository.save(newPassword);
            newUsers.setPasswords(newPassword);
            usersRepository.save(newUsers);
            passwordsRepository.deleteById(1);
            return newUsers;
        } else {
            newUsers = new Users("exist");
            return newUsers;
        }

    }*/

    //This method deletes an object with (Users)id.equals(id).
    @DeleteMapping("/deleteObj/{id}")
    public void deleteUser(@PathVariable(value = "id") int id) {
        System.out.println(id);
        //  List<Passwords> pas = new ArrayList<>(passwordsRepository.findById(0));
        passwordsRepository.deleteById(id);
    }
}
