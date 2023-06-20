package com.hyperskill.accountservice.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="payments")
public class Payment {
    @Id
    @GeneratedValue
    @Column(name="payment_id")
    private Integer id;

    @Column(name = "payment_employee")
    private String employee;

    @Column(name = "payment_period")
    private Date period;

    @Min(0)
    @Column(name = "payment_salary")
    private Long salary;
}
