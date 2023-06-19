package com.hyperskill.accountservice.controllers;


import com.hyperskill.accountservice.models.User;
import com.hyperskill.accountservice.dtos.UserDTO;
import com.hyperskill.accountservice.services.UserService;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")

public class AuthController implements IAuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    @PostMapping("/signup")
    public ResponseEntity<?> addUser(@Validated @RequestBody User newUser){
        UserDTO userResponse = this.modelMapper.map(this.userService.addUser(newUser),UserDTO.class);
        return new ResponseEntity<UserDTO>(userResponse, HttpStatus.OK);
    }

}
