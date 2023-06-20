package com.hyperskill.accountservice.requests;

import lombok.Data;

@Data
public class RoleRequest {
    private String user;
    private String role;
    private String operation;
}


