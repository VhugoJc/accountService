package com.hyperskill.accountservice.requests;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RoleRequest {
    private String user;
    private String role;
    @Pattern(regexp = "GRANT|REMOVE", message = "Use only GRAND and REMOVE operations!")
    private String operation;
}


