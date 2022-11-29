package com.example.Contacts.service;

import com.example.Contacts.domain.ContactsUsers;
import com.example.Contacts.domain.Role;
import com.example.Contacts.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    // private final UserRepo userRepo;
//    public UserService(UserRepo userRepo){
//        this.userRepo = userRepo;           //при инициализации бина,компонента спринг увидел зависимости и сделал inject
//    }

    @Autowired MailSender mailSender;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByCuserLogin(username);
    }

    public boolean addUser (ContactsUsers user, String username, String password){
        ContactsUsers userFromDb = userRepo.findByCuserLogin(user.getCuserLogin());

        if(userFromDb != null){
            return false;
        }

        user.setCuserLogin(username);
        user.setCuserPassword(password);
        user.setActive(false);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());

        userRepo.save(user);

        if (!StringUtils.isEmpty(user.getEmail())){
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome to MyContacts. Please, visit http://localhost:8080/activate/%s",
                    user.getCuserLogin(),
                    user.getActivationCode()
            );

            mailSender.send(user.getEmail(), "Activation code", message);
        }

        return true;
    }

    public boolean activateUser(String code) {
        ContactsUsers user = userRepo.findByActivationCode(code);
        if (user == null){
            return false;
        }
        user.setActivationCode(null);
        user.setActive(true);
        userRepo.save(user);

        return true;
    }
}
