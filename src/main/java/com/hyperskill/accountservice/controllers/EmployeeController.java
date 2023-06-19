package com.hyperskill.accountservice.controllers;

import com.hyperskill.accountservice.dtos.UserDTO;
import com.hyperskill.accountservice.models.User;
import com.hyperskill.accountservice.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/empl")
public class EmployeeController implements IEmployeeController {
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    //GET api/empl/payment
    @GetMapping("/payment")
    public ResponseEntity<?> getPayrool(Principal principal){
        User userData = this.userService.getUserByEmail(principal.getName());
        UserDTO userResponse = modelMapper.map(userData,UserDTO.class);
        return new ResponseEntity<UserDTO>(userResponse, HttpStatus.ACCEPTED);
    }
}
