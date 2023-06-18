package com.hyperskill.accountservice.controllers;

import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface IBusinessController {
    public ResponseEntity<?> getPayrool(Principal principal);
}
