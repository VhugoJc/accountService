package com.hyperskill.accountservice.services;

import com.hyperskill.accountservice.models.User;
import com.hyperskill.accountservice.requests.AccessRequest;
import com.hyperskill.accountservice.requests.RoleRequest;
import com.hyperskill.accountservice.responses.ChangePassResponse;
import com.hyperskill.accountservice.responses.StatusResponse;
import com.hyperskill.accountservice.responses.StatusUserResponse;

import java.util.List;

public interface IUserService {
    public User addUser(User newUser);
    public User getUserByEmail(String email);
    public ChangePassResponse changePass(String email, String password);
    public boolean userExist(String email);
    public List<User> getAllUsers();
    public StatusUserResponse deleteUser(String email);
    public User updateRole(RoleRequest roleData);

    public StatusResponse updateLocked(AccessRequest accessRequest);

    public void updateUSer(User userToUpdate);
}
