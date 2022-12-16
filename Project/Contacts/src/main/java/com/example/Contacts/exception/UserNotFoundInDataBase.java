package com.example.Contacts.exception;

import org.springframework.security.core.AuthenticationException;

public class UserNotFoundInDataBase extends AuthenticationException {
    public UserNotFoundInDataBase(String msg) {
        super(msg);
    }
}