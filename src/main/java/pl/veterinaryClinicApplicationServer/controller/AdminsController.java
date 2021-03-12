package pl.veterinaryClinicApplicationServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.veterinaryClinicApplicationServer.model.DateOfTheVisit;
import pl.veterinaryClinicApplicationServer.model.Users;
import pl.veterinaryClinicApplicationServer.repository.*;
import pl.veterinaryClinicApplicationServer.service.MailService;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping({"/"})
@RestController
public class AdminsController {
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

    @Autowired
    public DateOfTheVisitRepository dateOfTheVisitRepository;

    //Returns all saved visits
    @GetMapping("/appointments")
    public DateOfTheVisit[] appointments(){
        List<DateOfTheVisit> datesList = new LinkedList<>(dateOfTheVisitRepository.findAll());
        DateOfTheVisit[] datesArray = new DateOfTheVisit[datesList.size()];
        for(int i = 0; i < datesList.size(); i++){
            datesArray[i] = datesList.get(i);
        }
        return  datesArray;
    }

    @PostMapping("/deleteAnAppointment")
    public void deleteAnAppointment(@RequestBody DateOfTheVisit dateOfTheVisit){
        System.out.println("aaa");
        System.out.println(dateOfTheVisit.getDateof());
        List<DateOfTheVisit> da = Arrays.asList(dateOfTheVisitRepository.findByDateof(dateOfTheVisit.getDateof()).get(0));
        System.out.println(da.size());
        System.out.println(dateOfTheVisitRepository.findByDateof(dateOfTheVisit.getDateof()).get(0));
        dateOfTheVisit.setUsers(null);

        dateOfTheVisitRepository.delete(dateOfTheVisit);
    }
    @GetMapping("/usersForAppointmentsManagment")
    public void usersForAppointmentsManagment(@RequestBody Users[] users){

    }


}
