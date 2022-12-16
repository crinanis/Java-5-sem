package com.example.Contacts.controller;


import com.example.Contacts.domain.dto.ContactsUsers;
import com.example.Contacts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserRest {

    @Autowired
    private UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<ContactsUsers> getUsers(){
        return userService.findAll();
    }

}
