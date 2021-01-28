package com.pl.radoslawKopec.vetApp.VetClinicAppServer.Controller;

import com.pl.radoslawKopec.vetApp.VetClinicAppServer.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
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
    public void getDates() {

    }

    @GetMapping("/findAllMain")
    public List<Users> getAllPassport() {
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
    @PostMapping("/findUser2")
    public void checkPassword(@RequestBody Users users) {
        List<Users> idList = new ArrayList<>();
        idList = usersRepository.findByNickname(users.getNickname());

    }

    @PostMapping("/findUser")
    public Users checkPassword3(@RequestBody Users users) {
        System.out.println("duppa");

        List<Users> usersList = new ArrayList<>(usersRepository.findByNameAndLastname(users.getName(), users.getLastname()));
        System.out.println(usersList.size());
        if (usersList.size() == 0) {
            Users falseUser = new Users(0, "false", "false", "false", users.getPasswords(), users.getDateOfTheVisit());
            return falseUser;
        }
        //List<Passwords> passwordsList = new ArrayList<>(passwordsRepository.findByPassword(users));

        return users;
    }

    @PostMapping("/addTerms")
    public Users addTerms(@RequestBody Users users){
        List<Users> nickname = new ArrayList<>(usersRepository.findByNickname(users.getNickname()));
        nickname.get(0).setDateOfTheVisit(users.getDateOfTheVisit());
        Users instanceForAddTerm = nickname.get(0);
        usersRepository.save(instanceForAddTerm);
        return users;
    }

    //Zwraca wolne terminy
    @GetMapping("/getTerms")
    public String[] getVisitTerms() {
        //pobieranie daty(calendar) , formatowanie jej do formatu date
        DateOfTheVisit dateOfInstance = new DateOfTheVisit();
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd/HHmm");
        Date date = calendar.getTime();
        String todayDate = dateFormat.format(date);
        //String todayTest = new String("2020-12-11");
        String[] dates = new String[7];
        boolean nextMouth = false;
        //boolean nextYear = false;
        System.out.println(calendar.getTime());

        for (int i = 0; i < dates.length; i++) {
            //czy koniec miesiąca
            if ((calendar.getActualMaximum(Calendar.DAY_OF_MONTH) != calendar.DATE) && (nextMouth == false)) {
                calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE) + 1);
                dates = dateOfInstance.isWeekend(calendar, dates, i);
                // System.out.println(dates[i]);
            } else if ((calendar.getActualMaximum(Calendar.DAY_OF_MONTH) == calendar.DATE) && (calendar.getActualMaximum(Calendar.MONTH)) == 11) {
                calendar.set(calendar.get(Calendar.YEAR) + 1, calendar.get(Calendar.MONTH) + 1, 1);
                calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                dates = dateOfInstance.isWeekend(calendar, dates, i);
                // System.out.println(dates[i]);

            } else if ((calendar.getActualMaximum(Calendar.DAY_OF_MONTH) == calendar.DATE) && (calendar.getActualMaximum(Calendar.MONTH)) != 11) {
                calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, 1);
                calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                dates = dateOfInstance.isWeekend(calendar, dates, i);
                //System.out.println(dates[i]);
            }
        }
        String[] termsTimes = {"8:00", "8:30", "9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30"};
        String[] rewrittenDates = dateOfInstance.rewriteArray(dates, termsTimes);


        int reservedTerms = 0;
        for(int i=0;i<rewrittenDates.length;i++){
        if(dateOfTheVisitRepository.findByDateof(rewrittenDates[i]).size() != 0){
            reservedTerms++;
        }
        }
        String[] rewrittenDatesV2 = new String[rewrittenDates.length-reservedTerms];
        reservedTerms=0;
        for(int i=0;i<rewrittenDates.length;i++){
            if(dateOfTheVisitRepository.findByDateof(rewrittenDates[i]).size() != 0){
                reservedTerms++;
            }else{
                rewrittenDatesV2[i-reservedTerms] = rewrittenDates[i];
            }
        }
        //System.out.println(terms.get(0)+"  "+ terms.get(1));

        return rewrittenDatesV2;
    }


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
    }

    //Method get object(name,last name password. Then method findByNameAndLastname looking for users with matching values
    // and try to match password to user. Afrter correct match method send to client usej obj.
    @PostMapping("/userLogInPath")
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
    }

    //przysylamy id uzytkownika po zalogowaniu
    @GetMapping("/userAccount/{id}")
    public Users userAccout(@PathVariable(value = "id") String id) {
        List<Users> tmp = new ArrayList<>(usersRepository.findById(1));
        System.out.println(id);
        return tmp.get(0);
    }

    //creater user (but with horrible solution because id iteration makes some many problem
    // first : create user with password id 1
    // then save user i make password id equals user id
    // delete passsword id 1
    @PostMapping(path = "/create")
    public Users createUser(@RequestBody Users users) {
        List<Users> idList = new ArrayList<>(usersRepository.findByNickname(users.getNickname()));
        Users newUsers;
        if (idList.size() == 0) {
            idList.removeAll(idList);
            users.getPasswords().setId(1);
            newUsers = new Users(users.getNickname(), users.getName(), users.getLastname(), users.getPasswords());
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

        //return false;
        // return users;
    }


    @DeleteMapping("/deleteObj/{id}")
    public void deleteUser(@PathVariable(value = "id") int id) {
        System.out.println(id);
        //  List<Passwords> pas = new ArrayList<>(passwordsRepository.findById(0));
        passwordsRepository.deleteById(id);


    }

    @DeleteMapping("/deleteObj1/{id}")
    public void deleteUser1(@PathVariable(value = "id") int id) {
        // System.out.println(id);
        //  List<Users> pas = new ArrayList<>(usersRepository.findById(id));
        // System.out.println(pas.size());
        usersRepository.deleteById(id);
        //   System.out.println("usun");
        //List<Passwords> pas1 = new ArrayList<>(passwordsRepository.findAll());
        // System.out.println("dziala");
        //return pas1.size();

    }

    public boolean antyDuplicateNick(List<Users> list) {
        int shorteningLoop = 1;
        for (int i = 0; i < list.size(); i++) {
            for (int j = shorteningLoop; j < list.size(); j++) {
                if (list.get(i).getNickname().equals(list.get(j).getNickname())) {
                    System.out.println("there is");
                    return false;
                }
            }
            shorteningLoop++;
        }
        System.out.println("nope");
        return true;
    }


}
