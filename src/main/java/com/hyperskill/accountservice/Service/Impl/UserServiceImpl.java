package com.hyperskill.accountservice.Service.Impl;

import com.hyperskill.accountservice.Model.User;
import com.hyperskill.accountservice.Repository.UserRepository;
;
import com.hyperskill.accountservice.Service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User createUser(User newUser) {
        return newUser;
    }

}
