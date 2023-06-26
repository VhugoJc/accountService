package com.hyperskill.accountservice.controllers;

import com.hyperskill.accountservice.models.Log;
import com.hyperskill.accountservice.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
  The SecurityController class handles security-related operations.
  It provides endpoints for retrieving security logs.
 */

@RestController
@RequestMapping("/api/security")
public class SecurityController implements ISecurityController{
    @Autowired
    LogService logService;

    //Retrieve all security logs.
    @Override
    @GetMapping("/events")
    public ResponseEntity<?> getLogs() {
        return new ResponseEntity<List<Log>>(this.logService.getAllLogs(), HttpStatus.OK);
    }
}
