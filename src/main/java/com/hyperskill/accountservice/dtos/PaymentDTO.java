package com.hyperskill.accountservice.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class PaymentDTO {

    private String employee;

    private String period;

    @Min(0)
    private Long salary;
}
