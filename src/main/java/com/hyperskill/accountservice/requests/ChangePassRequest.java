package com.hyperskill.accountservice.requests;

import lombok.Data;

@Data
public class ChangePassRequest {
    private String new_password;
}