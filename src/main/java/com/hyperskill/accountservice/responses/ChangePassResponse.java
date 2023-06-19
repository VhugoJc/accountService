package com.hyperskill.accountservice.responses;

import lombok.Data;

@Data
public class ChangePassResponse {
    private String email;
    private String status;
}
