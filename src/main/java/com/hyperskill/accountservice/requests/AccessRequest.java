package com.hyperskill.accountservice.requests;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccessRequest {
    private String user;
    @Pattern(regexp = "LOCK|UNLOCK", message = "Use only LOCK and UNLOCK operations!")
    private String operation;
}
