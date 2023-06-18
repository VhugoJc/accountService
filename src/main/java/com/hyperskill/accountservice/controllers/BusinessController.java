package com.hyperskill.accountservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/empl")
public class BusinessController implements IBusinessController {
    //GET api/empl/payment
    @GetMapping("/payment")
    public ResponseEntity<?> getPayrool(){
        return new ResponseEntity<String>("hello world", HttpStatus.ACCEPTED);
    }

}
