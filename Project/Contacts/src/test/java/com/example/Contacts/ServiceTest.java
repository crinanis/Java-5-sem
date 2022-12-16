package com.example.Contacts;

import com.example.Contacts.domain.dto.ContactsList;
import com.example.Contacts.domain.dto.ContactsUsers;
import com.example.Contacts.domain.dto.Role;
import com.example.Contacts.repos.ContactsRepo;
import com.example.Contacts.repos.UserRepo;
import com.example.Contacts.service.MailSender;
import com.example.Contacts.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private ContactsRepo contactsRepo;

    @Test
    public void getUserByEmail()
    {
        Assert.assertNotNull(userRepository.findByCuserLogin("ksu"));
    }

    @Test
    public void isAdmin()
    {
        ContactsUsers user = new ContactsUsers();
        user.getRoles().add(Role.ADMIN);
        Assert.assertTrue(user.isAdmin());
    }

    @Test
    public void getUserById() {
        List<ContactsList> contactsList = contactsRepo.findByContactName("Ksusha5");
        Assert.assertNotNull(contactsList);
    }
}
