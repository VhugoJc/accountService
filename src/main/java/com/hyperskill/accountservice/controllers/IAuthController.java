package com.hyperskill.accountservice.controllers;

import com.hyperskill.accountservice.models.User;
import com.hyperskill.accountservice.requests.ChangePassRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface IAuthController {
    public ResponseEntity<?> addUser( User newUser,  HttpServletRequest request);
    public ResponseEntity<?> updatePassword(ChangePassRequest changePassRequest, Principal principal, HttpServletRequest request);
}
