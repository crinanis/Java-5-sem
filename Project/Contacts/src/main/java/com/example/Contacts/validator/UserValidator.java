package com.example.Contacts.validator;

import com.example.Contacts.domain.dto.ContactsUsers;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return ContactsUsers.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ContactsUsers usr =(ContactsUsers) o;
        if(usr.getUserID()<0){
            errors.rejectValue("id","negative value");
        }
    }
}