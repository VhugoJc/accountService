package com.hyperskill.accountservice.services;

import com.hyperskill.accountservice.models.User;
import com.hyperskill.accountservice.responses.ChangePassResponse;

public interface IUserService {
    public User addUser(User newUser);
    public User getUserByEmail(String email);
    public ChangePassResponse changePass(String email, String password);
    public boolean userExist(String email);

}
