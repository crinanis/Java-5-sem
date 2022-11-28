package com.example.Contacts.controller;

import com.example.Contacts.domain.ContactsUsers;
import com.example.Contacts.domain.Role;
import com.example.Contacts.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;
    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(ContactsUsers user, Map<String, Object> model){
        ContactsUsers userFromDb = userRepo.findByCuserLogin(user.getCuserLogin());

        if(userFromDb!= null){
            model.put("message", "User exists!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
        return "redirect:/login";
    }
}
