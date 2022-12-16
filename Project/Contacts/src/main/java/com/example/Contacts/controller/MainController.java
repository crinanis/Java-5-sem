package com.example.Contacts.controller;

import com.example.Contacts.domain.dto.ContactsList;
import com.example.Contacts.domain.dto.ContactsUsers;
import com.example.Contacts.repos.ContactsRepo;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
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
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<ContactsList> contacts = contactsRepo.findAll();

        if (filter != null && !filter.isEmpty()) {
            contacts = contactsRepo.findByContactName(filter);
        } else {
            contacts = contactsRepo.findAll();
        }

        model.addAttribute("contacts", contacts);
        model.addAttribute("filter", filter);
        return "main";
    }

    @Operation(
            summary = "User's registration",
            description = "Allows you to register a user"
    )
    @PostMapping("/main")
    public String addContact(
            @AuthenticationPrincipal ContactsUsers user,
            @RequestParam("file") MultipartFile file,
            @Valid ContactsList contactList,
            BindingResult bindingResult,
            Model model
    ) throws IOException {
        contactList.setContactOwner(user);

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("contactList", contactList);
        } else {
            saveFile(file, contactList);
            model.addAttribute("contactList", null);
            contactsRepo.save(contactList); // Шаг 1: сохранили данные
        }

        Iterable<ContactsList> contacts = contactsRepo.findAll();
        model.addAttribute("contacts", contacts); //Шаг 2: взяли данные из репозитория, положили в модель и отдали пользователю

        return "main";
    }

    @Operation(
            summary = "Function to save file",
            description = "Allows you to save file"
    )
    private void saveFile(MultipartFile file, ContactsList contactList) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            contactList.setFilename(resultFilename);
        }
    }

    @GetMapping("/user-contacts/{user}")
    public String userContacts(
            @AuthenticationPrincipal ContactsUsers currentUser,
            @PathVariable ContactsUsers user,
            Model model,
            @RequestParam(required = false) ContactsList contact
    ){
        Set<ContactsList> contacts = user.getContacts();

        model.addAttribute("contacts", contacts);
        model.addAttribute("contact", contact);
        model.addAttribute("isCurrentUser", currentUser.equals(user));

        return "userContacts";
    }

    @GetMapping("/del-user-contacts/{user}")
    public String deleteMessage(
            @PathVariable Long user,
            @RequestParam("contact") Long contactID
    ) throws IOException {
        contactsRepo.deleteById(contactID);

        return "redirect:/user-contacts/" + user;
    }

    @PostMapping("/user-contacts/{user}")
    public String updateMessage(
        @AuthenticationPrincipal ContactsUsers currentUser,
        @PathVariable Long user,
        @RequestParam("contactID") ContactsList contact,
        @RequestParam("contactName") String name,
        @RequestParam("contactNumber") String number,
        @RequestParam("file") MultipartFile file
    ) throws IOException {
        if (contact.getContactOwner().equals(currentUser)){
            if(!StringUtils.isEmpty(name)){
                contact.setContactName(name);
            }
            if(!StringUtils.isEmpty(number)){
                contact.setContactNumber(number);
            }

            saveFile(file, contact);
            contactsRepo.save(contact);
        }

        return "redirect:/user-contacts/" + user;
    }
}