package com.hyperskill.accountservice.controllers;

import com.hyperskill.accountservice.models.User;
import org.springframework.http.ResponseEntity;

public interface IAuthController {
    public ResponseEntity<?> addUser( User newUser);
}
