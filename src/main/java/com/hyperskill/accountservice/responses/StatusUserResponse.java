package com.hyperskill.accountservice.responses;

import lombok.Data;

@Data
public class StatusUserResponse {
    private String user;
    private String status;

    public StatusUserResponse(String user, String status) {
        this.user = user;
        this.status = status;
    }
}
