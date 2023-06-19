package com.hyperskill.accountservice.dtos;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String lastname;
    private String email;
    private List<String> roles;
   }
