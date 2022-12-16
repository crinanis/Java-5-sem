package com.example.Contacts.repos;
import com.example.Contacts.domain.dto.ContactsList;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ContactsRepo extends CrudRepository<ContactsList, Long> {
    List<ContactsList> findByContactName(String name);
}
