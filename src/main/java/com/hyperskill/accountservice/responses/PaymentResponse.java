package com.hyperskill.accountservice.responses;

import lombok.Data;

@Data
public class PaymentResponse {
private String period;
private String salary;

    public PaymentResponse(String period, String salary) {
        this.period = period;
        this.salary = salary;
    }
}
