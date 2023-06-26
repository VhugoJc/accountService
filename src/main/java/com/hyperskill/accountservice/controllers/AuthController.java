package com.hyperskill.accountservice.controllers;


import com.hyperskill.accountservice.logs.Event;
import com.hyperskill.accountservice.responses.ChangePassResponse;
import com.hyperskill.accountservice.requests.ChangePassRequest;
import com.hyperskill.accountservice.models.User;
import com.hyperskill.accountservice.dtos.UserDTO;
import com.hyperskill.accountservice.services.LogService;
import com.hyperskill.accountservice.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/auth")



public class AuthController implements IAuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private LogService logService;
    @Autowired
    private ModelMapper modelMapper;


    // Add a new user.
    @Override
    @PostMapping("/signup")
    public ResponseEntity<?> addUser(@Validated @RequestBody User newUser,  HttpServletRequest request){
        UserDTO userResponse = this.modelMapper.map(this.userService.addUser(newUser),UserDTO.class);

        this.logService.addLog(Event.CREATE_USER,"Anonymous",userResponse.getEmail(),request.getRequestURI());

        return new ResponseEntity<UserDTO>(userResponse, HttpStatus.OK);
    }

    // Update user password.
    @Override
    @PostMapping("/changepass")
    public ResponseEntity<?> updatePassword(@RequestBody ChangePassRequest changePassRequest, Principal principal, HttpServletRequest request) {
        ChangePassResponse entityResponse = this.userService.changePass( principal.getName(), changePassRequest.getNew_password());

        this.logService.addLog(Event.CHANGE_PASSWORD,principal.getName(),principal.getName(),request.getRequestURI());

        return new ResponseEntity<ChangePassResponse>(entityResponse,HttpStatus.OK);
    }

}
