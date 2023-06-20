package com.hyperskill.accountservice.responses;

import lombok.Data;

import javax.management.ConstructorParameters;

@Data

public class EmpPaymentResponse {
    private String name;
    private String lastname;
    private String period;
    private String salary;

    public EmpPaymentResponse(String name, String lastname, String period, String salary) {
        this.name = name;
        this.lastname = lastname;
        this.period = period;
        this.salary = salary;
    }
}
