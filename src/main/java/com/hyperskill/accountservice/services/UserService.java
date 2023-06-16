package com.hyperskill.accountservice.services;

import com.hyperskill.accountservice.models.User;
import com.hyperskill.accountservice.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements IUserService{
    @Autowired
    IUserRepository userRepository;
    public User addUser(User newUser){
        return userRepository.save(newUser);
    }
}
