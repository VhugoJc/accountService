package com.hyperskill.accountservice.controllers;

import com.hyperskill.accountservice.models.User;
import org.springframework.http.ResponseEntity;

public interface IAdminController {
    public ResponseEntity<?> getUsers(); //GET api/admin/user
    public ResponseEntity<?> deleteUser(String username); //DELETE api/admin/user
    public ResponseEntity<?> updateUserRole(); //PUT api/admin/user/role
}
