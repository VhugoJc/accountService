package com.hyperskill.accountservice.controllers;


import com.hyperskill.accountservice.models.User;
import com.hyperskill.accountservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> addUser(@RequestBody User newUser){
        User userResponse = this.userService.addUser(newUser);
        return new ResponseEntity<User>(userResponse, HttpStatus.OK);
    }

}
