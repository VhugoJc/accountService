package com.hyperskill.accountservice.controllers;

import org.springframework.http.ResponseEntity;

public interface ISecurityController {
    public ResponseEntity<?> getLogs();// GET api/security/events

}
