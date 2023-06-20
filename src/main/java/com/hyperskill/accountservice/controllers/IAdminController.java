package com.hyperskill.accountservice.controllers;

import com.hyperskill.accountservice.models.User;
import com.hyperskill.accountservice.requests.AccessRequest;
import com.hyperskill.accountservice.requests.RoleRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface IAdminController {
    public ResponseEntity<?> getUsers(); //GET api/admin/user
    public ResponseEntity<?> deleteUser(String username, Principal principal, HttpServletRequest request); //DELETE api/admin/user
    public ResponseEntity<?> updateUserRole(RoleRequest roleData,Principal principal, HttpServletRequest request); //PUT api/admin/user/role
    public ResponseEntity<?> updateUserAccess(AccessRequest accessRequest, Principal principal, HttpServletRequest request);//PUT api/admin/user/access
}
