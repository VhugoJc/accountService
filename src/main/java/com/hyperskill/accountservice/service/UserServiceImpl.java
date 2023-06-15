package com.hyperskill.accountservice.service;

import com.hyperskill.accountservice.model.User;
import com.hyperskill.accountservice.repository.UserRepository;

public class UserServiceImpl implements UserService{
    private  final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User newUser) {
        return userRepository.save(newUser);
    }

}
