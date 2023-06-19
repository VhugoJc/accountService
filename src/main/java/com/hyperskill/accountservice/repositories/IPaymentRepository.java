package com.hyperskill.accountservice.repositories;

import com.hyperskill.accountservice.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findAllByEmployee(String employee);
}
