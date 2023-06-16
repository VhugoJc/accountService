package com.hyperskill.accountservice.controllers;


import com.hyperskill.accountservice.exception.ErrorMessage;
import com.hyperskill.accountservice.models.User;
import com.hyperskill.accountservice.payloads.UserDTO;
import com.hyperskill.accountservice.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/auth")
public class AuthController implements IAuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/signup")
    public ResponseEntity<?> addUser(@Validated @RequestBody User newUser){
        if(!this.userService.emailValidation(newUser.getEmail())){ //if email validation fails
            ErrorMessage errorMsg = new ErrorMessage(
                    new Date(),
                    HttpStatus.BAD_REQUEST.value(),
                    "Bad Request",
                    "/api/auth/signup"
            );
            return new ResponseEntity<>(errorMsg, HttpStatus.BAD_REQUEST);
        }
        UserDTO userResponse = this.modelMapper.map(this.userService.addUser(newUser),UserDTO.class);
        return new ResponseEntity<UserDTO>(userResponse, HttpStatus.OK);
    }

}
