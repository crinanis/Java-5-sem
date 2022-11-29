package com.example.Contacts.controller;

import com.example.Contacts.domain.ContactsList;
import com.example.Contacts.domain.ContactsUsers;
import com.example.Contacts.repos.ContactsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {
    private final ContactsRepo contactsRepo;
    public MainController(ContactsRepo contactsRepo) {
        this.contactsRepo = contactsRepo;
    }

    @Value("${upload.path}")
    private String uploadPath;

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
            @RequestParam String number, Map<String, Object> model,
            @RequestParam("file")MultipartFile file
            ) throws IOException {

        ContactsList newContact = new ContactsList(name, number, user);

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            newContact.setFilename(resultFilename);
        }

        contactsRepo.save(newContact); // Шаг 1: сохранили данные

        Iterable<ContactsList> contacts = contactsRepo.findAll();
        model.put("contacts", contacts); //Шаг 2: взяли данные из репозитория, положили в модель и отдали пользователю

        return "main";
    }

}