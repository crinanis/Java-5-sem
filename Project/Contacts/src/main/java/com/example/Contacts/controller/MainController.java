package com.example.Contacts.controller;

import com.example.Contacts.domain.ContactsList;
import com.example.Contacts.domain.ContactsUsers;
import com.example.Contacts.repos.ContactsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    private final ContactsRepo contactsRepo;

    public MainController(ContactsRepo contactsRepo) {
        this.contactsRepo = contactsRepo;
    }

    @GetMapping("/")
    public String greeting(
            @RequestParam(name="name", required=false, defaultValue="World") String name,
            Map<String, Object> model
    )
    {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model){
        Iterable<ContactsList> contacts = contactsRepo.findAll();
        model.put("contacts", contacts);

        return "main";
    }

    @PostMapping("/main")
    public String addContact(
            @AuthenticationPrincipal ContactsUsers user,
            @RequestParam String name,
            @RequestParam String number, Map<String, Object> model
    ){
        ContactsList newContact = new ContactsList(name, number, user);
        contactsRepo.save(newContact); // Шаг 1: сохранили данные

        Iterable<ContactsList> contacts = contactsRepo.findAll();
        model.put("contacts", contacts); //Шаг 2: взяли данные из репозитория, положили в модель и отдали пользователю

        return "main";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Map<String, Object> model){
        Iterable<ContactsList> contacts;
        if(filter != null && !filter.isEmpty()){
            contacts = contactsRepo.findByContactName(filter);
        } else {
            contacts = contactsRepo.findAll();
        }
        model.put("contacts", contacts);

        return "main";
    }
}