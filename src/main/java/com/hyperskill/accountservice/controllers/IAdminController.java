package com.hyperskill.accountservice.controllers;

import com.hyperskill.accountservice.models.User;
import com.hyperskill.accountservice.requests.AccessRequest;
import com.hyperskill.accountservice.requests.RoleRequest;
import org.springframework.http.ResponseEntity;

public interface IAdminController {
    public ResponseEntity<?> getUsers(); //GET api/admin/user
    public ResponseEntity<?> deleteUser(String username); //DELETE api/admin/user
    public ResponseEntity<?> updateUserRole(RoleRequest roleData); //PUT api/admin/user/role
    public ResponseEntity<?> updateUserAccess(AccessRequest accessRequest);//PUT api/admin/user/access
}
