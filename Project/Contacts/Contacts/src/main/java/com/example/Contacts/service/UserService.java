package com.example.Contacts.service;

import com.example.Contacts.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    // private final UserRepo userRepo;
//    public UserService(UserRepo userRepo){
//        this.userRepo = userRepo;           //при инициализации бина,компонента спринг увидел зависимости и сделал inject
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByCuserLogin(username);
    }
}
