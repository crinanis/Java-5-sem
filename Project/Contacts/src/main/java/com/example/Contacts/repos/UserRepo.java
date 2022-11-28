package com.example.Contacts.repos;

import com.example.Contacts.domain.ContactsUsers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<ContactsUsers, Long> {
    ContactsUsers findByCuserLogin(String username);
}
