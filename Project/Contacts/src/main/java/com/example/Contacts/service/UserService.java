package com.example.Contacts.service;

import com.example.Contacts.domain.dto.ContactsUsers;
import com.example.Contacts.domain.dto.Role;
import com.example.Contacts.exception.UserNotFoundInDataBase;
import com.example.Contacts.repos.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired MailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String cuserLogin) throws UserNotFoundInDataBase {
        ContactsUsers contactsUsers = userRepo.findByCuserLogin(cuserLogin);
        if (cuserLogin == "") {
            throw new UserNotFoundInDataBase("Login is null");
        }
        if (contactsUsers == null) {
            throw new UserNotFoundInDataBase("User is not found");
        }

        return contactsUsers;
    }

    public boolean addUser (ContactsUsers user){
        ContactsUsers userFromDb = userRepo.findByCuserLogin(user.getCuserLogin());

        if(userFromDb != null){
            return false;
        }

        user.setActive(false);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setCuserPassword(passwordEncoder.encode(user.getCuserPassword()));

        userRepo.save(user);

        sendMessage(user);

        return true;
    }

    private void sendMessage(ContactsUsers user) {
        if (!StringUtils.isEmpty(user.getEmail())){
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome to MyContacts. Please, visit http://localhost:8080/activate/%s",
                    user.getCuserLogin(),
                    user.getActivationCode()
            );

            mailSender.send(user.getEmail(), "Activation code", message);
        }
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

    public List<ContactsUsers> findAll() {
        return userRepo.findAll();
    }

    public void saveUser(ContactsUsers user, String username, Map<String, String> form) {
        user.setCuserLogin(username);
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepo.save(user);
    }

    public void updateProfile(ContactsUsers user, String password, String email) {
        String userEmail = user.getEmail();

        boolean isEmailChanged = (email != null && !email.equals(userEmail)) ||
                (userEmail != null && !userEmail.equals(email));

        if (isEmailChanged) {
            user.setEmail(email);

            if (!StringUtils.isEmpty(email)) {
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }

        if (!StringUtils.isEmpty(password)) {
            user.setCuserPassword(passwordEncoder.encode(password));
        }

        user.setActivationCode(null);
        userRepo.save(user);

        if (isEmailChanged) {
            sendMessage(user);
        }
    }
}
