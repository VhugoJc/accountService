package com.hyperskill.accountservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

public interface IEmployeeController {
    public ResponseEntity<?> getPayrool(@RequestParam String period, Principal principal);
}
