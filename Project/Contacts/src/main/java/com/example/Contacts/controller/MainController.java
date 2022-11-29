package com.example.Contacts.controller;

import com.example.Contacts.domain.ContactsList;
import com.example.Contacts.domain.ContactsUsers;
import com.example.Contacts.repos.ContactsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String greeting(Map<String, Object> model)
    {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model){
        Iterable<ContactsList> contacts = contactsRepo.findAll();

        if(filter != null && !filter.isEmpty()){
            contacts = contactsRepo.findByContactName(filter);
        } else {
            contacts = contactsRepo.findAll();
        }

        model.addAttribute("contacts", contacts);
        model.addAttribute("filter", filter);
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

}