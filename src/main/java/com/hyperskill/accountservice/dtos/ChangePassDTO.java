package com.hyperskill.accountservice.dtos;

import lombok.Data;

@Data
public class ChangePassDTO {
    private String email;
    private String status;
}
