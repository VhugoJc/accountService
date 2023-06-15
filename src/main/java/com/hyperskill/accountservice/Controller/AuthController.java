package com.hyperskill.accountservice.Controller;

import com.hyperskill.accountservice.Model.User;
import com.hyperskill.accountservice.Payload.UserDTO;
import com.hyperskill.accountservice.Service.Impl.UserServiceImpl;
import com.hyperskill.accountservice.Service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private ModelMapper modelMapper;


    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@Valid @RequestBody User newUser){
        User createdUser = this.userServiceImpl.createUser(newUser);
        UserDTO userResponse = this.modelMapper.map(createdUser,UserDTO.class);
        return new ResponseEntity<UserDTO>(userResponse, HttpStatus.OK);
    }
}
