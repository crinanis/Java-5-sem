package com.example.Contacts;

import com.example.Contacts.domain.ContactsList;
import com.example.Contacts.repos.ContactsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.print.attribute.standard.JobKOctets;
import java.util.List;
import java.util.Map;

@Controller
public class GreetingController {
    @Autowired
    private ContactsRepo contactsRepo;

    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name="name", required=false, defaultValue="World") String name,
            Map<String, Object> model
    )
    {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping("/")
    public String main(Map<String, Object> model){
        Iterable<ContactsList> contacts = contactsRepo.findAll();
        model.put("encoding", "UTF-8");
        model.put("forceEncoding", "true");
        model.put("contacts", contacts);

        return "main";
    }

    @PostMapping("/")
    public String addContact(@RequestParam String name, @RequestParam String number, Map<String, Object> model){
        model.put("encoding", "UTF-8");
        model.put("forceEncoding", "true");
        ContactsList contactsList = new ContactsList(name, number);
        contactsRepo.save(contactsList); // Шаг 1: сохранили данные

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