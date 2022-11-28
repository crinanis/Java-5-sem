package com.example.Contacts.repos;
import com.example.Contacts.domain.ContactsList;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ContactsRepo extends CrudRepository<ContactsList, Integer> {
    List<ContactsList> findByContactName(String name);
}