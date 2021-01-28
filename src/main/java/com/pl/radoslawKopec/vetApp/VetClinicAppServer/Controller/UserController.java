package com.pl.radoslawKopec.vetApp.VetClinicAppServer.Controller;

import com.pl.radoslawKopec.vetApp.VetClinicAppServer.Model.User;
import com.pl.radoslawKopec.vetApp.VetClinicAppServer.Model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    public UserRepository userRepository;

    @GetMapping(path = "/users")
    public List<User> findAll(){
        List<User> userList = new ArrayList<>(userRepository.findAll());
        return userList;
    }
    @PostMapping(path = "/createUser")
    public User createUser(@RequestBody User user){
        User tmpUser = user;
        userRepository.save(tmpUser);
        return tmpUser;
    }

 /*   @PostMapping(path = "/findUser")
    public User findUser(@RequestBody User user){
        System.out.println(user.getName());
        System.out.println(user.getLastname());
      List<User> tmpList = new ArrayList<>(userRepository.findByNameAndLastname(user.getName(),user.getLastname()));
        System.out.println(tmpList.size());
      User tmpUser = new User(tmpList.get(0).getId(),tmpList.get(0).getName(),tmpList.get(0).getLastname());
      return tmpUser;
    }*/

}
