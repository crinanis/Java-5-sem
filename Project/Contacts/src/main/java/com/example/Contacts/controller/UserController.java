package com.example.Contacts.controller;

import com.example.Contacts.domain.ContactsUsers;
import com.example.Contacts.domain.Role;
import com.example.Contacts.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public String userList(Model model){
        model.addAttribute("users", userRepo.findAll());

        return "userList";
    }

    @GetMapping("/{user}")
    public String userEditForm(@PathVariable ContactsUsers user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());

        return "userEdit";
    }
}
