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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController implements IAdminController{
    @Autowired
    UserService userService;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/user")
    @Override
    public ResponseEntity<?> getUsers() {
        Type listType = new TypeToken<List<UserDTO>>(){}.getType();
        List<User> allUsersList = this.userService.getAllUsers();
        List<UserDTO> postDtoList = this.modelMapper.map(allUsersList,listType);
        return new ResponseEntity<>(postDtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteUser(String username) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateUserRole() {
        return null;
    }
}
