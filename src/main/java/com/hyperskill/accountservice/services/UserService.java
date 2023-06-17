package com.hyperskill.accountservice.services;

import com.hyperskill.accountservice.models.User;
import com.hyperskill.accountservice.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{
    @Autowired
    IUserRepository userRepository;
    public User addUser(User newUser){
        return userRepository.save(newUser);
    }

    @Override
    public boolean emailValidation(String email) {
        String domain = "@acme.com";
        List<User> user =  userRepository.findByEmail(email);
        if (user.size()!=0 || !email.contains(domain)){ // if the email exists or does not contain the domain
            return false;
        }else{
            return true;
        }
    }


}
