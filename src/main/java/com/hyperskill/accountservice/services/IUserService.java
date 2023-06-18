package com.hyperskill.accountservice.services;

import com.hyperskill.accountservice.models.User;

public interface IUserService {
    public User addUser(User newUser);
    public User getUserByEmail(String email);
}
